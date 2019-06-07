package com.ddd.movie.shiroRealm;

import com.ddd.movie.pojo.Order;
import com.ddd.movie.service.OrderService;
import com.ddd.movie.service.serviceImpl.OrderServiceImpl;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean("credentialsMatcher")
    public RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher() {
        RetryLimitHashedCredentialsMatcher retryLimitHashedCredentialsMatcher = new RetryLimitHashedCredentialsMatcher();
        retryLimitHashedCredentialsMatcher.setHashAlgorithmName("MD5");
        retryLimitHashedCredentialsMatcher.setHashIterations(2);
        //是否储存为16进制
//        retryLimitHashedCredentialsM1atcher.setStoredCredentialsHexEncoded(false);
        return retryLimitHashedCredentialsMatcher;
    }

    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("127.0.0.1:6379");
        return redisManager;
    }

    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        //ttl
        redisCacheManager.setExpire(300);
//        redisCacheManager.setPrincipalIdFieldName("id");
        return redisCacheManager;
    }

    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public ShiroRealm shiroRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        shiroRealm.setCredentialsMatcher(retryLimitHashedCredentialsMatcher());
        shiroRealm.setCachingEnabled(true);
        shiroRealm.setAuthenticationCachingEnabled(true);
        shiroRealm.setAuthorizationCachingEnabled(true);
        shiroRealm.setCacheManager(redisCacheManager());
        return shiroRealm;
    }

    @Bean
    public SessionListener sessionListener() {
        return new SessionListener() {
            @Override
            public void onStart(Session session) {

            }

            @Override
            public void onStop(Session session) {
                if (session.getAttribute("order") != null) {
                    OrderService orderService = new OrderServiceImpl();
                    orderService.add((Order) session.getAttribute("order"));
                }
            }

            @Override
            public void onExpiration(Session session) {
                if (session.getAttribute("order") != null) {
                    OrderService orderService = new OrderServiceImpl();
                    orderService.add((Order) session.getAttribute("order"));
                }
            }
        };
    }

    @Bean
    public SessionIdGenerator sessionIdGenerator() {
        return new JavaUuidSessionIdGenerator();
    }

    @Bean
    public SimpleCookie simpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("sessionId");
        simpleCookie.setPath("/");
        simpleCookie.setMaxAge(-1);
        return simpleCookie;
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        Collection<SessionListener> listeners = new ArrayList<>();
        listeners.add(sessionListener());
        sessionManager.setSessionListeners(listeners);
        sessionManager.setSessionIdCookie(simpleCookie());
        sessionManager.setGlobalSessionTimeout(300000);
        sessionManager.setDeleteInvalidSessions(true);

        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        securityManager.setSessionManager(sessionManager());
        securityManager.setCacheManager(redisCacheManager());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        filterChainDefinitionMap.put("/", "anon");
        filterChainDefinitionMap.put("/manage/**", "roles[admin]");
        filterChainDefinitionMap.put("/ticket*", "anon");
        filterChainDefinitionMap.put("/ticket/*/*", "anon");
        filterChainDefinitionMap.put("/ticket/order/**", "authc");
        filterChainDefinitionMap.put("/movie*", "anon");
        filterChainDefinitionMap.put("/movie/**", "anon");
        filterChainDefinitionMap.put("/cinema*", "anon");
        filterChainDefinitionMap.put("/cinema/**", "anon");

        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/logout", "logout");

        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/success");
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

}
