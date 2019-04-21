package com.shop.myshop.dao;

import com.shop.myshop.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Integer> {

    Category findByName(String name);
}
