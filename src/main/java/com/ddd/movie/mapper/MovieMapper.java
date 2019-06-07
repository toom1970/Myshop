package com.ddd.movie.mapper;

import com.ddd.movie.pojo.Movie;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("movieMapper")
public interface MovieMapper {

    @Select("select * from movie")
    List<Movie> findAll();

    @Select("select * from movie where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "tags", column = "id", many = @Many(select = "com.ddd.movie.mapper.TagMapper.findByIdToMovie")),
            @Result(property = "images", column = "id", many = @Many(select = "com.ddd.movie.mapper.ImageMapper.findByIdToMovie")),
            @Result(property = "photos", column = "id", many = @Many(select = "com.ddd.movie.mapper.PhotoMapper.findByIdToMovie"))
    })
    Movie findMovieById(int id);

    @Select("select * from movie where name = #{name}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "tags", column = "id", many = @Many(select = "com.ddd.movie.mapper.TagMapper.findByIdToMovie")),
            @Result(property = "images", column = "id", many = @Many(select = "com.ddd.movie.mapper.ImageMapper.findByIdToMovie")),
            @Result(property = "photos", column = "id", many = @Many(select = "com.ddd.movie.mapper.PhotoMapper.findByIdToMovie"))
    })
    Movie findMovieByName(String name);
}
