package com.ddd.movie.service;

import com.ddd.movie.pojo.Movie;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();

    List<Movie> findAllMybatis();

    Movie getById(int id);

    Movie findByName(String name);

    int add(Movie movie);

    int delete(Movie movie);

    int update(Movie movie);

    public Page<Movie> findJpa(Integer page, Integer size);
}