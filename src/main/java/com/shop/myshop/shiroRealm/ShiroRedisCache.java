package com.shop.myshop.shiroRealm;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ShiroRedisCache implements Cache {
    private String prefix;
    private RedisTemplate redisTemplate;

    public ShiroRedisCache(String prefix, RedisTemplate redisTemplate) {
        this.prefix = prefix;
        this.redisTemplate = redisTemplate;
    }

    public String getPrefix() {
        return prefix + ":";
    }

    @Override
    public Object get(Object o) throws CacheException {
        if (o == null)
            return null;
        return redisTemplate.opsForValue().get(o);
    }

    @Override
    public Object put(Object o, Object o2) throws CacheException {
        if (o == null || o2 == null)
            return null;
        redisTemplate.opsForValue().set(o, o2);
        return o2;
    }

    @Override
    public Object remove(Object o) throws CacheException {
        if (o == null)
            return null;
        Object v = redisTemplate.opsForValue().get(o);
        redisTemplate.delete(o);
        return v;
    }

    @Override
    public void clear() throws CacheException {
        redisTemplate.getConnectionFactory().getConnection().flushDb();
    }

    @Override
    public int size() {
        return redisTemplate.getConnectionFactory().getConnection().dbSize().intValue();
    }

    @Override
    public Set keys() {
        Set<Object> keys = redisTemplate.keys(getPrefix() + "*");
        Set<Object> set = new HashSet<>();
        for (Object o : keys)
            set.add(o);
        return set;
    }

    @Override
    public Collection values() {
        return null;
    }
}
