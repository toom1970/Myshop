package com.ddd.movie.mapper;

import com.ddd.movie.pojo.Area;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("areaMapper")
public interface AreaMapper {
    @Select("select * from area where cid = #{cid}")
    Area findToCinema(int cid);
}
