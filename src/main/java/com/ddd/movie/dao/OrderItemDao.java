package com.ddd.movie.dao;

import com.ddd.movie.pojo.Orderitem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemDao extends JpaRepository<Orderitem, Integer> {
}
