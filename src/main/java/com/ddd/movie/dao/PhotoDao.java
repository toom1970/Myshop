package com.ddd.movie.dao;

import com.ddd.movie.pojo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoDao extends JpaRepository<Photo, Integer> {
}
