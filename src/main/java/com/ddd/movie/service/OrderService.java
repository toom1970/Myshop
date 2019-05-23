package com.ddd.movie.service;

import com.ddd.movie.pojo.Movie;
import com.ddd.movie.pojo.Order;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrderService {

    List<Order> findAll();

    PageInfo findPageByMybatis(Integer page, Integer size);

    Order findById(int id);

    long add(Order order);

    long delete(Order order);

    long update(Order order);

}
