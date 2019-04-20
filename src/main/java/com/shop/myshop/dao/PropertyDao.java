package com.shop.myshop.dao;

import com.shop.myshop.pojo.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyDao extends JpaRepository<Property, Integer> {
}
