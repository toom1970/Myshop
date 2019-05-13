package com.ddd.movie.service;

import com.ddd.movie.pojo.User;

import java.util.HashSet;
import java.util.List;

public interface UserService {
    List<User> findAll();

    User getById(int id);

    User findByName(String name);

    int add(User user);

    int delete(User user);

    int update(User user);
}
