package com.shop.myshop.service.serviceImpl;

import com.shop.myshop.dao.UserDao;
import com.shop.myshop.pojo.User;
import com.shop.myshop.service.UserService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {
    @Resource(name = "userDao")
    UserDao userDao;

    @Override
    @Cacheable(key = "'All'")
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    @Cacheable(key = "#id")
    public User getById(int id) {
        return userDao.getOne(id);
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
    @CacheEvict(key = "#user.getId()")
    public int delete(User user) {
        userDao.delete(user);
        return 0;
    }

    @Override
    @CachePut(key = "#user.getId()")
    public int update(User user) {
        User saved = userDao.saveAndFlush(user);
        return saved.getId();
    }
}
