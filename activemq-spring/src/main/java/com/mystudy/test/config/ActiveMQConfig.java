package com.mystudy.test.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;

/**
 * @author dalaoban
 * @create 2020-08-21-12:37
 */
@Configuration
public class ActiveMQConfig {

    @Value("${spring.activemq.broker-url}")
    private String urlA ;

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){
        return new ActiveMQConnectionFactory(urlA);

    }

    @DependsOn("activeMQConnectionFactory")
    @Bean
    public Connection connection(@Autowired ActiveMQConnectionFactory activeMQConnectionFactory) throws JMSException {
        activeMQConnectionFactory.setUseAsyncSend(true);
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        return connection ;
    }

    @DependsOn("connection")
    @Bean
    public Session session(@Autowired Connection connection) throws JMSException {
        return connection.createSession(true,Session.AUTO_ACKNOWLEDGE);
    }
}
