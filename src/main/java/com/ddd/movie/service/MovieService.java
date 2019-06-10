package com.ddd.movie.service;

import com.ddd.movie.pojo.Movie;
import com.github.pagehelper.PageInfo;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface MovieService {
    List<Movie> findAll(int id);

    PageInfo findPageByMybatis(Integer page, Integer size);

    Movie findById(int id);

    Movie findByName(String name);

    Movie findByIdJson(int id);

    int MoviePageNum(String url);

    int add(Movie movie);

    int delete(Movie movie);

    int update(Movie movie);

    Page<Movie> findPageByJpa(Integer page, Integer size);
}