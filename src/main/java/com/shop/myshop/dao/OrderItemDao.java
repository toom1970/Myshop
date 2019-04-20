package com.shop.myshop.dao;

import com.shop.myshop.pojo.Orderitem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemDao extends JpaRepository<Orderitem, Integer> {
}
