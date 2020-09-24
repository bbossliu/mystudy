package com.mystudy.lx.send;

import com.mystudy.lx.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author dalaoban
 * @create 2020-08-01-14:07
 */
public class RabbitMQSendTest {

    public static void main(String[] args) {
        sendMessage("你好吗？");
    }

    public static void sendMessage(String message){

        try {
            Connection connection = ConnectionUtil.getConnection();

            Channel channel = connection.createChannel();

            channel.exchangeDeclare(ConnectionUtil.EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

            //队列是否持久化  是否排他（不能被其他消费），是否自动删除 ， 队列大小
            channel.queueDeclare(ConnectionUtil.QUEUE_NAME,true,false,false,null);

            //fanout模式不需要绑定路由键
            channel.queueBind(ConnectionUtil.QUEUE_NAME,ConnectionUtil.EXCHANGE_NAME,"",null);

            channel.basicPublish(ConnectionUtil.EXCHANGE_NAME,"",null,message.getBytes());

            System.out.println("发送的消息为："+message);

            channel.close();

            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

}
