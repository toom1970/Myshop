package com.ddd.movie.mapper;

import com.ddd.movie.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Mapper
@Repository("userMapper")
public interface UserMapper {
    @Select("select * from user where id = #{id}")
    @Results({
            @Result(property = "id", column = "id", jdbcType = JdbcType.INTEGER),
            @Result(property = "name", column = "name"),
            @Result(property = "password", column = "password"),
            @Result(property = "salt", column = "salt"),
            @Result(property = "roles", column = "id", many = @Many(select = "com.ddd.movie.mapper.RoleMapper.findByIdToUser"))
    })
    User findById(int id);
}
