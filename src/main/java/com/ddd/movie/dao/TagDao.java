package com.ddd.movie.dao;

import com.ddd.movie.pojo.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagDao extends JpaRepository<Tag, Integer> {

    Tag findByName(String name);
}
