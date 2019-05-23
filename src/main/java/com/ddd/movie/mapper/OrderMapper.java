package com.ddd.movie.mapper;

import com.ddd.movie.pojo.Order;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("orderMapper")
public interface OrderMapper {
    @Select("select * from order_")
    @Results({
            @Result(property = "id", column = "id", jdbcType = JdbcType.BIGINT),
            @Result(property = "releaseInfo", column = "rid", one = @One(select = "com.ddd.movie.mapper.ReleaseInfoMapper.findById")),
            @Result(property = "user", column = "uid", one = @One(select = "com.ddd.movie.mapper.UserMapper.findById"))
    })
    List<Order> findAll();

    @Select("select * from order_ where id = #{id}")
    @Results({
            @Result(property = "id", column = "id", jdbcType = JdbcType.BIGINT),
            @Result(property = "releaseInfo", column = "rid", one = @One(select = "com.ddd.movie.mapper.ReleaseInfoMapper.findById")),
            @Result(property = "user", column = "uid", one = @One(select = "com.ddd.movie.mapper.UserMapper.findById"))
    })
    Order findById(long id);

    @Insert("insert into order_ (id,uid,rid,number,status,price) values(#{id},#{user.getId()}, #{releaseInfo.getId()}, #{number}, #{status},#{price} ")
    long add(Order order);

    @Delete("delete from order_ where id = #{id}")
    long delete(Order order);

    @Update("update order_ set uid=#{user.getId()},rid=#{releaseInfo.getId()},number=#{number},status=#{status},price=#{price} where id=#{id}")
    long update(Order order);
}
