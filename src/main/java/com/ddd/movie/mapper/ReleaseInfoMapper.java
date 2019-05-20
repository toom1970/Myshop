package com.ddd.movie.mapper;

import com.ddd.movie.pojo.ReleaseInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("releaseInfoMapper")
public interface ReleaseInfoMapper {
    //    @Select("select movie.id mid," +
//            "movie.`name`," +
//            "movie.director," +
//            "movie.releaseDate,cinema.id cid," +
//            "cinema.`name`," +
//            "cinema.area," +
//            "cinema.contactnumber," +
//            "cinema.cinemaservice," +
//            "releaseinfo.id," +
//            "releaseinfo.time," +
//            "releaseinfo.price," +
//            "releaseinfo.remainticket from releaseinfo join movie on releaseinfo.mid= movie.id join cinema on releaseinfo.cid = cinema.id")
    @Select("select * from releaseinfo")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "movie", column = "mid", one = @One(select = "com.ddd.movie.mapper.MovieMapper.findMovieById")),
            @Result(property = "cinema", column = "cid", one = @One(select = "com.ddd.movie.mapper.CinemaMapper.findCinemaById"))
    })
    List<ReleaseInfo> findAll();
}
