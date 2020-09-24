package com.mystudy.lx;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author dalaoban
 * @create 2020-06-21-16:40
 */
public class TestCountDownLatch {

    public static void main(String[] args) {
        //计数器为10，表示等待10个线程全部结束后开始执行
        CountDownLatch countDownLatch = new CountDownLatch(10);

        try {
            MyThread myThread = new MyThread(countDownLatch);
            long start = System.currentTimeMillis();
           for (int i=0;i<10;i++){
               Thread thread = new Thread(myThread);
               thread.start();
           }
           //等待计数器计数为0
            countDownLatch.await();

            long end = System.currentTimeMillis();

            System.out.println("耗时："+(end-start));

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class MyThread implements Runnable{

    private CountDownLatch countDownLatch;

    public MyThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            //睡眠3秒钟
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //计数器-1
            countDownLatch.countDown();
        }
    }
}
