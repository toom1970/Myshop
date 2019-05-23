package com.ddd.movie.dao;

import com.ddd.movie.pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer> {
}
