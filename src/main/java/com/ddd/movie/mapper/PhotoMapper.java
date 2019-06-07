package com.ddd.movie.mapper;

import com.ddd.movie.pojo.Photo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("photoMapper")
public interface PhotoMapper {
    @Select("select * from tag where mid = #{id}")
    Photo findByIdToMovie(int id);
}
