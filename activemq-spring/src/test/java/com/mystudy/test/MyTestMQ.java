package com.mystudy.test;

import com.mystudy.test.product.QueueProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

/**
 * @author dalaoban
 * @create 2020-08-20-23:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ActiveMQApplication.class)
@WebAppConfiguration
public class MyTestMQ {

    @Resource    //  这个是java 的注解，而Autowried 是 spring 的
    private QueueProduct queueProduct ;

    //  这个是java 的注解，而Autowried 是 spring 的
    @Test
    public  void testSend() throws Exception{
        queueProduct.produceMessage();
    }


}
