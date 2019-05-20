package com.ddd.movie.service;

import com.ddd.movie.pojo.Cinema;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CinemaService {
    public Cinema findById(int id);

    public Cinema findByName(String name);

    List<Cinema> findAll();

    int add(Cinema cinema);

    int delete(Cinema cinema);

    int update(Cinema cinema);

    PageInfo findPageByMybatis(Integer page,Integer size);
}
