package com.ddd.movie.mapper;

import com.ddd.movie.pojo.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MovieMapper {
    @Select("select * from movie")
    List<Movie> findAll();
}
