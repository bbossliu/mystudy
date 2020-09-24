package com.mystudy.activemq.tx;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author dalaoban
 * @create 2020-08-20-21:30
 */
public class ActiveMQTXProduct {

    private static final String ACTIVEMQ_URL = "tcp://192.168.2.129:61616";
    private static final String ACTIVEMQ_QUEUE_NAME = "Queue-TX01";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);

        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        //1.创建会话session，两个参数transacted=事务,acknowledgeMode=确认模式(签收)
        //设置为开启事务
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue(ACTIVEMQ_QUEUE_NAME);

        MessageProducer producer = session.createProducer(queue);

        try {

            for (int i = 0; i < 4; i++) {
                TextMessage textMessage = session.createTextMessage("msg--" + i);

                producer.send(textMessage);

                if(i==3){
//                    throw new RuntimeException("发生异常");
                }
            }
            session.commit();
            System.out.println("消息发送完成");
        }catch (Exception e){
            System.out.println("发生异常回滚发送的消息。");
            session.rollback();
        }finally {
            producer.close();
            session.close();
            connection.close();
        }
    }
}
