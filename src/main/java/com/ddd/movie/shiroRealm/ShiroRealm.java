package com.ddd.movie.shiroRealm;

import com.ddd.movie.pojo.Role;
import com.ddd.movie.service.UserService;
import com.ddd.movie.pojo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@EnableCaching
public class ShiroRealm extends AuthorizingRealm {

    @Resource(name = "userService")
    UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
//        int id = (int)principalCollection.getPrimaryPrincipal();
        User userReal = userService.getById(user.getId());
        Set<String> roles = new HashSet<>();
        for (Role roleType : userReal.getRoles()) {
            System.out.println(userReal.getRoles());
            System.out.println(roleType.getRoleValue());
            roles.add(roleType.getRoleValue());
        }
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String userName = (String) usernamePasswordToken.getPrincipal();
        User user = userService.findByName(userName);
        if (user == null)
            throw new AccountException("User Not Exist");
        ByteSource salt = new ShiroSimpleByteSource(user.getSalt());
        return new SimpleAuthenticationInfo(user, user.getPassword(), salt, getName());
    }
}
