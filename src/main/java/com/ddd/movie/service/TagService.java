package com.ddd.movie.service;

import com.ddd.movie.pojo.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findAll();

    Tag getById(int id);

    Tag findByName(String name);

    int add(Tag tag);

    int delete(Tag tag);

    int update(Tag tag);

}
