package com.ddd.movie.dao;

import com.ddd.movie.pojo.Movie;
import org.springframework.data.jpa.repository.JpaRepository;



public interface MovieDao extends JpaRepository<Movie, Integer> {

}
