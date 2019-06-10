package com.ddd.movie.mapper;

import com.ddd.movie.pojo.Cinema;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("cinemaMapper")
public interface CinemaMapper {

    @Select("select * from cinema")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "area", column = "id", one = @One(select = "com.ddd.movie.mapper.AreaMapper.findToCinema")),
            @Result(property = "service", column = "cinemaService")
    })
    List<Cinema> findAll();

    @Select("select * from cinema where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "area", column = "id", one = @One(select = "com.ddd.movie.mapper.AreaMapper.findToCinema")),
            @Result(property = "service", column = "cinemaService")
    })
    Cinema findCinemaById(int id);
}
