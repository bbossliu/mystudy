package com.mystudy.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author dalaoban
 * @create 2020-08-20-15:34
 */
public class ActiveMqNoSyncConsumer {

    public static final String ACTIVEMQ_URL = "tcp://192.168.2.129:61616";
    public static final String QUEUE_NAME = "queue06";


    public static void main(String[] args) throws JMSException, IOException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);

        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue(QUEUE_NAME);

        MessageConsumer consumer = session.createConsumer(queue);

        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if(message instanceof TextMessage){
                    TextMessage textMessage = (TextMessage) message;
//                    try {
//                        TimeUnit.SECONDS.sleep(1);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    try {
                        System.out.println(textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        // 让主线程不要结束。因为一旦主线程结束了，其他的线程（如此处的监听消息的线程）也都会被迫结束。
        // 实际开发中，我们的程序会一直运行，这句代码都会省略。

        System.in.read();
        consumer.close();
        session.close();
        connection.close();

    }


}
