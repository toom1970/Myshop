package com.shop.myshop.service;

import com.shop.myshop.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User getById(int id);

    User findByName(String name);

    int add(User user);

    int delete(User user);

    int update(User user);
}
