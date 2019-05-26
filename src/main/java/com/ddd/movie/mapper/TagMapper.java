package com.ddd.movie.mapper;

import com.ddd.movie.pojo.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TagMapper {
    @Select("select * from tag where mid = #{id}")
    Tag findByIdToMovie(int id);
}
