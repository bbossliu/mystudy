package com.mystudy.redis.controller;

import com.mystudy.redis.entity.Order;
import com.mystudy.redis.entity.R;
import com.mystudy.redis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dalaoban
 * @create 2020-08-19-10:55
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/test")
    public Integer insertOrder(Order order){
        return orderService.insertOrder(order);
    }


    @GetMapping("/selectid")
    public R selectOrderById(Integer id){
        return orderService.selectOrderById(id);
    }

}
