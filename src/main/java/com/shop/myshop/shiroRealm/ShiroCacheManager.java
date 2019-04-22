package com.shop.myshop.shiroRealm;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

public class ShiroCacheManager implements CacheManager {
    @Resource(name = "shiroRedisTemplate")
    RedisTemplate<String, String> redisTemplate;

//    public ShiroCacheManager() {
//    }
//
//    protected Cache createCache(String s) throws CacheException {
//        return new ShiroRedisCache(s, redisTemplate);
//    }

    @Override
    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
        if (s == null)
            return null;
        return new ShiroRedisCache(s, redisTemplate);
    }
}
