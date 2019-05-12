package com.ddd.movie.shiroRealm;

import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class ShiroCacheManager extends AbstractCacheManager {
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    protected Cache createCache(String s) throws CacheException {
        return new ShiroRedisCache(s, redisTemplate);
    }
//
//    @Override
//    public <K, V> Cache<K, V> getCache(String s) throws CacheException {
//        if (s == null)
//            return null;
//        return new ShiroRedisCache(s, redisTemplate);
//    }
}
