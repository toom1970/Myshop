package com.ddd.movie.service.serviceImpl;

import com.ddd.movie.mapper.OrderMapper;
import com.ddd.movie.pojo.Order;
import com.ddd.movie.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Resource(name = "orderMapper")
    OrderMapper orderMapper;

    @Override
    public List<Order> findAll() {
        return orderMapper.findAll();
    }

    @Override
    public PageInfo findPageByMybatis(Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<Order> pagelist = orderMapper.findAll();
        return new PageInfo<>(pagelist);
    }

    @Override
    public Order findById(int id) {
        return orderMapper.findById(id);
    }

    @Override
    public long add(Order order) {
        orderMapper.add(order);
        return order.getId();
    }

    @Override
    public long delete(Order order) {
        orderMapper.delete(order);
        return 0;
    }

    @Override
    public long update(Order order) {
        orderMapper.update(order);
        return order.getId();
    }
}
