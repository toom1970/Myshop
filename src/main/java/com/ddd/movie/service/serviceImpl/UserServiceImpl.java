package com.ddd.movie.service.serviceImpl;

import com.ddd.movie.mapper.UserMapper;
import com.ddd.movie.service.UserService;
import com.ddd.movie.dao.UserDao;
import com.ddd.movie.pojo.User;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {
    @Resource(name = "userDao")
    UserDao userDao;
    @Resource(name = "userMapper")
    UserMapper userMapper;

    @Override
    @Cacheable(key = "'all'")
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
//    @Cacheable(key = "#id")
    public User getById(int id) {
        return userMapper.findById(id);
    }

    @Override
    @Cacheable(key = "#name")
    public User findByName(String name) {
        return userDao.findByName(name);
    }

    @Override
    public int add(User user) {
        User saved = userDao.saveAndFlush(user);
        return saved.getId();
    }

    @Override
    @Caching(evict = {
            @CacheEvict(key = "#user.getId()"),
            @CacheEvict(key = "#user.getName()"),
            @CacheEvict(key = "'all'")})
    public int delete(User user) {
        userDao.delete(user);
        return 0;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(key = "#user.getId()"),
            @CacheEvict(key = "#user.getName()"),
            @CacheEvict(key = "'all'")})
    public int update(User user) {
        User saved = userDao.saveAndFlush(user);
        return saved.getId();
    }
}
