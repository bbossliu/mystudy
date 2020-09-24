package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author dalaoban
 * @create 2020-05-30-19:36
 */
@SpringBootApplication
@MapperScan(basePackages = "com.mystudy.lx.mapper")
public class MyOauthResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyOauthResourceApplication.class,args);
    }
}
