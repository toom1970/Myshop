package com.ddd.movie.dao;

import com.ddd.movie.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User, Integer> {
    User findByName(String name);
}
