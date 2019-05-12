package com.ddd.movie.dao;

import com.ddd.movie.pojo.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyDao extends JpaRepository<Property, Integer> {
}
