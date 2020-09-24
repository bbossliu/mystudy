package com.mystudy.lx;

import com.sun.imageio.plugins.common.I18N;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author dalaoban
 * @create 2020-06-27-15:48
 */
public class TestCPDemo {

    //共享队列
    BlockingQueue<Integer> queue;

    Integer num;

    public TestCPDemo(BlockingQueue<Integer> queue,Integer num) {
        this.queue = queue;
        this.num = num;
    }

    public synchronized void productCar01(){
       try {

           boolean b = queue.offer(num, 2, TimeUnit.SECONDS);
           if(b){
               num+=1;
               System.out.println("生产者生产："+num);
               notifyAll();
               TimeUnit.SECONDS.sleep(1);
           } else {
               wait();
           }
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    public synchronized void ConsumerCar01(){
        try {
            Integer poll = queue.poll(2, TimeUnit.SECONDS);
            if(poll!=null){
                System.out.println("消费者消费："+poll);
                num-=1 ;
                notifyAll();
                TimeUnit.SECONDS.sleep(1);
            } else {
                wait();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class Product01 implements Runnable{

    TestCPDemo testCPDemo;

    public Product01(TestCPDemo testCPDemo) {
        this.testCPDemo = testCPDemo;
    }

    @Override
    public void run() {
        while (true){
            testCPDemo.productCar01();
        }
    }
}

class Consumer01 implements Runnable{

    TestCPDemo testCPDemo;

    public Consumer01(TestCPDemo testCPDemo) {
        this.testCPDemo = testCPDemo;
    }


    @Override
    public void run() {
        while (true){
            testCPDemo.ConsumerCar01();
        }
    }
}

class TestCP01{
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> blockingQueue =  new ArrayBlockingQueue<>(10);
        TestCPDemo testCPDemo = new TestCPDemo(blockingQueue, 1);

        Thread t1 = new Thread(new Product01(testCPDemo));
        Thread t2 = new Thread(new Consumer01(testCPDemo));

        t1.start();
        t2.start();
    }
}


