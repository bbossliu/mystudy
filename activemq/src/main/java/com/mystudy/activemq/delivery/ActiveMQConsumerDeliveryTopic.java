package com.mystudy.activemq.delivery;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author dalaoban
 * @create 2020-08-20-18:43
 */
public class ActiveMQConsumerDeliveryTopic {
    public static final String ACTIVEMQ_URL = "tcp://192.168.2.129:61616";
    public static final String TOPIC_NAME = "topic01";

    public static void main(String[] args) throws JMSException, IOException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);

        Connection connection = activeMQConnectionFactory.createConnection();

        // 设置客户端ID。向MQ服务器注册自己的名称
        connection.setClientID("marrry");

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic = session.createTopic(TOPIC_NAME);

        // 创建一个topic订阅者对象。一参是topic，二参是订阅者名称
        TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic,"remark...");

        // 之后再开启连接
        connection.start();

        topicSubscriber.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if(message instanceof TextMessage){
                    try {
                        System.out.println("收到的持久化 topic："+((TextMessage) message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        System.in.read();
        topicSubscriber.close();
        session.close();
        connection.close();

    }
}
