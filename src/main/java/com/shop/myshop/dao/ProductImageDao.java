package com.shop.myshop.dao;

import com.shop.myshop.pojo.Productimage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageDao extends JpaRepository<Productimage, Integer> {
}
