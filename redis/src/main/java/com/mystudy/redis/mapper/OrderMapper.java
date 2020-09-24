package com.mystudy.redis.mapper;

import com.mystudy.redis.entity.Order;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author dalaoban
 * @create 2020-08-19-10:49
 */
public interface OrderMapper {

    @Select("insert into t_order (id,name) values (#{id},#{name})")
    Integer insertOrder(Order order);

    @Select("select * from t_order where id=#{id}")
    Order selectOrderById(Integer id);

    @Select("select * from t_order")
    List<Order> selectOrderyAll();
}
