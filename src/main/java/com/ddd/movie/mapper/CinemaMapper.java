package com.ddd.movie.mapper;

import com.ddd.movie.pojo.Cinema;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("cinemaMapper")
public interface CinemaMapper {

    @Select("select * from cinema")
    List<Cinema> findAll();

    @Select("select * from cinema where id = #{id}")
    Cinema findCinemaById(int id);
}
