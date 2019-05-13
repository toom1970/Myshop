package com.ddd.movie.dao;


import com.ddd.movie.pojo.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyValueDao extends JpaRepository<PropertyValue, Integer> {
}
