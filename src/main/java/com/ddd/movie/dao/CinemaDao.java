package com.ddd.movie.dao;

import com.ddd.movie.pojo.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaDao extends JpaRepository<Cinema, Integer> {
    Cinema findByName(String name);
}
