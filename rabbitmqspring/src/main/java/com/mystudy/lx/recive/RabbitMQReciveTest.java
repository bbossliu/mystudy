package com.mystudy.lx.recive;

import com.mystudy.lx.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author dalaoban
 * @create 2020-08-01-14:07
 */
public class RabbitMQReciveTest {

    public static void main(String[] args) {
        getMessage();
    }

    public static void getMessage(){

        try {
            Connection connection = ConnectionUtil.getConnection();
            Channel channel = connection.createChannel();

            DefaultConsumer defaultCallback  = new DefaultConsumer(channel){

                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                    System.out.println(new String(body,"UTF-8"));

                }
            };

            channel.basicConsume(ConnectionUtil.QUEUE_NAME,defaultCallback);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

}
