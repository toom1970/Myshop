package com.ddd.movie.mapper;

import com.ddd.movie.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RoleMapper {
    @Select("select * from role where uid = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "roleValue", column = "value")
    })
    public Role findByIdToUser(int id);
}
