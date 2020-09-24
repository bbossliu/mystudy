package com.mystudy.redis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dalaoban
 * @create 2020-08-19-10:04
 */
@SpringBootApplication
@MapperScan(basePackages = "com.mystudy.redis.mapper")
public class SpringBootRedisApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootRedisApplication.class,args);
    }
}
