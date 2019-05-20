package com.ddd.movie.mapper;

import com.ddd.movie.pojo.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("movieMapper")
public interface MovieMapper {

    @Select("select * from movie")
    List<Movie> findAll();

    @Select("select * from movie where id = #{id}")
    Movie findMovieById(int id);
}
