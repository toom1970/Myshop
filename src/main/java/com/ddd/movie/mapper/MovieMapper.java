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
            @Result(property = "tags", column = "id", many = @Many(select = "com.ddd.movie.mapper.TagMapper.findByIdToMovie"))
    })
    Movie findMovieById(int id);
}
