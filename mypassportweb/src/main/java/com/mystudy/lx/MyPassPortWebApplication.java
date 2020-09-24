package com.mystudy.lx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dalaoban
 * @create 2020-05-30-18:27
 */
@SpringBootApplication
@MapperScan(basePackages = "com.mystudy.lx.mapper")
public class MyPassPortWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyPassPortWebApplication.class,args);
    }
}
