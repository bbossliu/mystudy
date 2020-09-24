package com.mystudy.test.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.jms.Queue;
import java.util.UUID;

/**
 * @author dalaoban
 * @create 2020-08-20-22:34
 */
@Component
public class QueueProduct {

    @Autowired
    JmsMessagingTemplate jmsMessagingTemplate;

    // 这个是我们配置的队列目的地
    @Autowired
    Queue queue;

    // 发送消息
    public void produceMessage(){
        // 一参是目的地，二参是消息的内容
        jmsMessagingTemplate.convertAndSend(queue,"****"+ UUID.randomUUID().toString().substring(0,6));
    }

//    // 定时任务。每3秒执行一次。非必须代码，仅为演示。
//    @Scheduled(fixedDelay = 3000)
//    public void produceMessageScheduled(){
//        produceMessage();
//    }



}
