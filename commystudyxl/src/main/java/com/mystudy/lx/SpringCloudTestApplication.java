package com.mystudy.lx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author dalaoban
 * @create 2020-06-11-21:37
 */
@SpringBootApplication
@EnableEurekaServer
public class SpringCloudTestApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudTestApplication.class,args);
    }
}
