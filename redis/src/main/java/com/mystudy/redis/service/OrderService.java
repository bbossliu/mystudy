package com.mystudy.redis.service;


import com.mystudy.redis.entity.Order;
import com.mystudy.redis.entity.R;

import java.util.List;

public interface OrderService {
    Integer insertOrder(Order order);

    R selectOrderById(Integer id);

    List<Order> selectOrderyAll();

    R synchronizedSelectOrderById(Integer id);
}
