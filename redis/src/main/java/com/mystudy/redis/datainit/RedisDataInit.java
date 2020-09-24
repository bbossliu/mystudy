package com.mystudy.redis.datainit;

import com.mystudy.redis.entity.Order;
import com.mystudy.redis.filter.RedisBloomFilter;
import com.mystudy.redis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author dalaoban
 * @create 2020-08-19-10:35
 */
@Component
public class RedisDataInit {

    @Autowired
    OrderService orderService;

    @Autowired
    RedisBloomFilter redisBloomFilter;

    @PostConstruct
    public void init(){
        List<Order> orders = orderService.selectOrderyAll();
        for (Order order : orders) {
            redisBloomFilter.put(String.valueOf(order.getId()));
        }
    }
}
