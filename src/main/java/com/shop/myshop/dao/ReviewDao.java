package com.shop.myshop.dao;

import com.shop.myshop.pojo.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewDao extends JpaRepository<Review, Integer> {
}
