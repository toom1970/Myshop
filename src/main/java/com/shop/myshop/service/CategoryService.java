package com.shop.myshop.service;

import com.shop.myshop.pojo.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category getById(int id);

    Category findByName(String name);

    int add(Category category);

    int delete(Category category);

    int update(Category category);

}
