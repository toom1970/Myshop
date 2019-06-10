package com.ddd.movie.service;

import com.ddd.movie.pojo.Cinema;
import com.ddd.movie.pojo.Movie;
import com.ddd.movie.pojo.ReleaseInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CinemaService {
    public Cinema findById(int id);

    public Cinema findByIdJson(int id);

    public Cinema findByName(String name);

    List<Movie> findReleaseMovie(int id);

    public int cinemaPageNum(String url);

    List<ReleaseInfo> findReleaseInfo(int id);

    List<Cinema> findAll(String url);

    int add(Cinema cinema);

    int delete(Cinema cinema);

    int update(Cinema cinema);

    PageInfo findPageByMybatis(Integer page, Integer size);
}
