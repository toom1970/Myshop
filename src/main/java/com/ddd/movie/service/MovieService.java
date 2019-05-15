package com.ddd.movie.service;

import com.ddd.movie.pojo.Movie;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface MovieService {
    List<Movie> findAll();

//    List<Movie> findAllMybatis(Integer page, Integer size);
    PageInfo findAllMybatis(Integer page, Integer size);

    Movie getById(int id);

    Movie findByName(String name);

    int add(Movie movie);

    int delete(Movie movie);

    int update(Movie movie);

    public Page<Movie> findJpa(Integer page, Integer size);
//    public String findJpa(Integer page, Integer size);
}