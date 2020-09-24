package com.mystudy.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.store.kahadb.KahaDBTransactionStore;

import javax.jms.*;
import java.io.IOException;

/**
 * @author dalaoban
 * @create 2020-08-20-16:28
 */
public class ActiveMQConsumerTopic {

    public static final String ACTIVEMQ_URL = "tcp://192.168.2.129:61616";
    public static final String TOPIC_NAME = "topic05";

    public static void main(String[] args) throws JMSException, IOException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);

        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic(TOPIC_NAME);

        MessageConsumer consumer = session.createConsumer(topic);

        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if(message instanceof TextMessage){
                    try {
                        System.out.println("接收到的消息体："+((TextMessage) message).getText());
                        System.out.println("接收到的消息属性："+((TextMessage) message).getStringProperty("k1"));
                        System.out.println("接收到的消息属性："+((TextMessage) message).getIntProperty("k2"));
                        System.out.println("接收到的消息属性："+((TextMessage) message).getObjectProperty("k3"));
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        System.in.read();
        consumer.close();
        session.close();
        connection.close();

    }
}
