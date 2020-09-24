package com.mystudy.test.consumer;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

/**
 * @author dalaoban
 * @create 2020-08-20-23:11
 */
@Component
public class QueueConsummer {

    // 注册一个监听器。destination指定监听的主题。
    @JmsListener(destination = "Schedule02")
    public void receive(TextMessage textMessage) throws  Exception{
        System.out.println(" ***  消费者收到消息  ***"+textMessage.getText());
    }

}
