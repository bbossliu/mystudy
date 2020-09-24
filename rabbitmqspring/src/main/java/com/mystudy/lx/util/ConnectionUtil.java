package com.mystudy.lx.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author dalaoban
 * @create 2020-08-01-14:02
 */
public class ConnectionUtil {

    public static final String QUEUE_NAME = "rabQueueO" ;

    public static final String EXCHANGE_NAME = "rabExO" ;

    public static Connection getConnection () throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("120.79.61.78");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("virtual-rab-demoo");
        connectionFactory.setUsername("rabbitmq");
        connectionFactory.setPassword("rabbitmq");
        return connectionFactory.newConnection();
    }


}
