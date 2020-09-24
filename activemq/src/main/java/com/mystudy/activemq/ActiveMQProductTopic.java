package com.mystudy.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dalaoban
 * @create 2020-08-20-16:23
 */
public class ActiveMQProductTopic {

    public static final String ACTIVEMQ_URL = "tcp://192.168.2.129:61616";
    public static final String TOPIC_NAME = "topic05";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);

        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic(TOPIC_NAME);

        MessageProducer producer = session.createProducer(topic);

        for (int i = 0; i < 1; i++) {

            TextMessage textMessage = session.createTextMessage("topic--" + i);

            textMessage.setStringProperty("k1","v1");
            textMessage.setIntProperty("k2",2);
            Map<String, String> map = new HashMap<>();
            map.put("h1","t1");
            map.put("h2","t2");
            textMessage.setObjectProperty("k3",map);

            producer.send(textMessage);


        }

        producer.close();
        session.close();
        connection.close();
        System.out.println("消息发送完成");
    }
}
