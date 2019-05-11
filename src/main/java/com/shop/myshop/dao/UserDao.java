package com.shop.myshop.dao;

import com.shop.myshop.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User, Integer> {
    User findByName(String name);
}
