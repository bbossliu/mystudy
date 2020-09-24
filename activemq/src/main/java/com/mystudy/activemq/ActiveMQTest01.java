package com.mystudy.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author dalaoban
 * @create 2020-08-20-14:55
 */
public class ActiveMQTest01 {

    //  linux 上部署的activemq 的 IP 地址 + activemq 的端口号
    public static final String ACTIVEMQ_URL = "tcp://192.168.2.129:61616";
    // 目的地的名称
    public static final String QUEUE_NAME = "ELQ01";

    public static void main(String[] args) {
        // 1 按照给定的url创建连接工厂，这个构造器采用默认的用户名密码。该类的其他构造方法可以指定用户名和密码。
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        Connection connection = null ;
        try {
            // 2 通过连接工厂，获得连接 connection 并启动访问。
            connection = activeMQConnectionFactory.createConnection();
            connection.start();

            //创建session , 第一参数表示是否开启事务，第二个参数表示消息的签收模式
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //创建队列
            Queue queue = session.createQueue(QUEUE_NAME);

            //创建消息的发送者
            MessageProducer producer = session.createProducer(queue);
            //设置为非持久化
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            for (int i = 0; i < 10; i++) {
                TextMessage textMessage = session.createTextMessage("msg--" + i);

                producer.send(textMessage);
            }

            producer.close();
            session.close();
            connection.close();
            System.out.println("消息发送完成！");

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
