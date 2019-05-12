package com.ddd.movie.dao;

import com.ddd.movie.pojo.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewDao extends JpaRepository<Review, Integer> {
}
