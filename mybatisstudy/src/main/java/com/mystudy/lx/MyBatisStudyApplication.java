package com.mystudy.lx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author dalaoban
 * @create 2020-05-17-22:43
 */

@SpringBootApplication
@MapperScan(basePackages = "com.mystudy.lx.mapper")
public class MyBatisStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBatisStudyApplication.class,args);
    }
}
