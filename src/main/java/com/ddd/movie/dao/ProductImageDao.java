package com.ddd.movie.dao;

import com.ddd.movie.pojo.Productimage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageDao extends JpaRepository<Productimage, Integer> {
}
