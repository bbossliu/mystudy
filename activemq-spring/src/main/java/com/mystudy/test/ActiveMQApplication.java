package com.mystudy.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author dalaoban
 * @create 2020-08-20-22:30
 */
@SpringBootApplication
// 是否开启定时任务调度功能
@EnableScheduling
public class ActiveMQApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActiveMQApplication.class,args);
    }
}
