package com.ddd.movie.service;

import com.ddd.movie.pojo.Movie;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface MovieService {
    List<Movie> findAll(String url);

    PageInfo findPageByMybatis(Integer page, Integer size);

    Movie findById(int id);

    Movie findByName(String name);

    int add(Movie movie);

    int delete(Movie movie);

    int update(Movie movie);

    Page<Movie> findPageByJpa(Integer page, Integer size);
}