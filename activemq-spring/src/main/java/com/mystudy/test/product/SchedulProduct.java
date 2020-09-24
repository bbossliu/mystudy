package com.mystudy.test.product;

import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.*;

/**
 * @author dalaoban
 * @create 2020-08-21-13:31
 */
@Component
public class SchedulProduct {

    private String queueName= "Schedule02";

    @Autowired
    Session session;

    @PostConstruct
    public void send() throws JMSException {
        Queue queue = session.createQueue(queueName);

        MessageProducer producer = session.createProducer(queue);

        long delay =  2*1000;
        long period = 5*1000;
        int repeat = 3 ;

        for (int i = 0; i < 3; i++) {

            TextMessage textMessage = session.createTextMessage("msg---" + i);

            //延迟时间10s
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY,delay);

            //间隔多久发送一次
            textMessage.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD,period);

            //共发送几次
            textMessage.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT,repeat);

            producer.send(textMessage);
        }
        session.commit();
        System.out.println("消息发送完成");
    }
}
