package com.ddd.movie.mapper;

import com.ddd.movie.pojo.ReleaseInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("releaseInfoMapper")
public interface ReleaseInfoMapper {
    @Select("select * from releaseinfo")
    @Results({
            @Result(property = "id", column = "id", jdbcType = JdbcType.INTEGER),
            @Result(property = "movie", column = "mid", one = @One(select = "com.ddd.movie.mapper.MovieMapper.findMovieById")),
            @Result(property = "cinema", column = "cid", one = @One(select = "com.ddd.movie.mapper.CinemaMapper.findCinemaById"))
    })
    List<ReleaseInfo> findAll();

    @Select("select * from releaseinfo where id = #{id}")
    @Results({
            @Result(property = "id", column = "id", jdbcType = JdbcType.INTEGER),
            @Result(property = "movie", column = "mid", one = @One(select = "com.ddd.movie.mapper.MovieMapper.findMovieById")),
            @Result(property = "cinema", column = "cid", one = @One(select = "com.ddd.movie.mapper.CinemaMapper.findCinemaById"))
    })
    ReleaseInfo findById(int id);

    @Select("select * from releaseinfo where mid = #{mid} and cid = #{cid}")
    @Results({
            @Result(property = "id", column = "id", jdbcType = JdbcType.INTEGER),
            @Result(property = "movie", column = "mid", one = @One(select = "com.ddd.movie.mapper.MovieMapper.findMovieById")),
            @Result(property = "cinema", column = "cid", one = @One(select = "com.ddd.movie.mapper.CinemaMapper.findCinemaById"))
    })
    List<ReleaseInfo> findByMovieIdAndCinemaId(int mid, int cid);
}
