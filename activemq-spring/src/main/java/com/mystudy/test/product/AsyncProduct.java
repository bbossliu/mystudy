package com.mystudy.test.product;

import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.*;
import java.util.UUID;

/**
 * @author dalaoban
 * @create 2020-08-21-12:44
 */
@Component
public class AsyncProduct{

    @Autowired
    Session session ;

//    @PostConstruct
    public void sendMessage() throws JMSException {
        Queue queue = session.createQueue("hello1");
        ActiveMQMessageProducer producer =(ActiveMQMessageProducer) session.createProducer(queue);

        try {
            for (int i = 0; i < 4; i++) {
                ActiveMQMapMessage mqMapMessage = new ActiveMQMapMessage();

                mqMapMessage.setString("k1","v1");

                mqMapMessage.setJMSMessageID(UUID.randomUUID().toString().substring(0,6)+"liuxin");
                final String  msgId = mqMapMessage.getJMSMessageID();
                producer.send(mqMapMessage, new AsyncCallback() {
                    @Override
                    public void onSuccess() {
                        System.out.println("发送成功"+msgId);
                    }

                    @Override
                    public void onException(JMSException e) {
                        System.out.println("发送失败的消息为！"+msgId);
                    }
                });
                if(i==3){
                    throw new RuntimeException("模拟异常发生");
                }
            }

            session.commit();
            System.out.println("消息发送完成");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
//
//    @Scheduled(fixedDelay = 3000)
//    public void Schedule() throws JMSException {
//        sendMessage();
//    }
}
