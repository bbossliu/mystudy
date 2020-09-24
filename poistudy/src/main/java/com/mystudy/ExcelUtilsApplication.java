package com.mystudy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dalaoban
 * @create 2020-05-11-22:56
 */
@SpringBootApplication
@MapperScan("com.mystudy.lx.mapper")
public class ExcelUtilsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExcelUtilsApplication.class);
    }
}
