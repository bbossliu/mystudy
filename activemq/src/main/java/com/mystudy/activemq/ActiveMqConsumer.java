package com.mystudy.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author dalaoban
 * @create 2020-08-20-15:14
 */
public class ActiveMqConsumer {

    public static final String ACTIVEMQ_URL = "tcp://192.168.2.129:61616";
    public static final String QUEUE_NAME = "ELQ01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);

        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue(QUEUE_NAME);

        MessageConsumer consumer = session.createConsumer(queue);

        while (true){

            TextMessage textMessage = (TextMessage)consumer.receive();

            if(textMessage!=null){
                System.out.println("接收到消息："+textMessage.getText());
            }else {
                break;
            }
        }

        consumer.close();
        session.close();
        connection.close();
    }

}
