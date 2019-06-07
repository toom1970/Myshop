package com.ddd.movie.mapper;

import com.ddd.movie.pojo.Image;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("imageMapper")
public interface ImageMapper {
    @Select("select * from tag where mid = #{id}")
    Image findByIdToMovie(int id);
}
