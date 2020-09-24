package com.mystudy.activemq.delivery;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dalaoban
 * @create 2020-08-20-18:40
 */
public class ActiveProductDeliveryTopic {
    public static final String ACTIVEMQ_URL = "tcp://192.168.2.129:61616";
    public static final String TOPIC_NAME = "topic01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);

        Connection connection = activeMQConnectionFactory.createConnection();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic(TOPIC_NAME);

        MessageProducer producer = session.createProducer(topic);
        //设置该topic 为持久化消息
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        // 设置持久化topic之后再，启动连接
        connection.start();

        for (int i = 0; i < 1; i++) {

            TextMessage textMessage = session.createTextMessage("topic--" + i);

            producer.send(textMessage);


        }

        producer.close();
        session.close();
        connection.close();
        System.out.println("消息发送完成");
    }
}
