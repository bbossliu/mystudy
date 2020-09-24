package com.mystudy.test.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

/**
 * @author dalaoban
 * @create 2020-08-20-22:32
 */
@Configuration
// 开启jms适配
@EnableJms
public class QueueConfig {

    @Value("${myqueue}")
    private String myqueue;

    @Bean
    public ActiveMQQueue activeMQQueue(){
        return new ActiveMQQueue(myqueue);
    }
}
