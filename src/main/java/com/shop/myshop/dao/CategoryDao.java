package com.shop.myshop.dao;

import com.shop.myshop.pojo.Category;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//@Mapper
public interface CategoryDao extends JpaRepository<Category, Integer> {

    Category findByName(String name);
}
