package com.mystudy.lx;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author dalaoban
 * @create 2020-06-21-17:00
 */
public class TestCyclicBarrier {

    static class MyThread implements Runnable{
        //用于控制会议开始的屏障
        private CyclicBarrier barrier;

        //参会人员
        private String name;

        public MyThread(CyclicBarrier barrier, String name) {
            this.barrier = barrier;
            this.name = name;
        }

        public CyclicBarrier getBarrier() {
            return barrier;
        }

        public void setBarrier(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(new Random(100000).nextLong());
                System.out.println(name+"进入会议室");
                //等待其他线程加入
                barrier.await();
                System.out.println(name+"开始会议");
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        executorService.submit(new MyThread(cyclicBarrier,"zs"));
        executorService.submit(new MyThread(cyclicBarrier,"ls"));
        executorService.submit(new MyThread(cyclicBarrier,"wy"));

        executorService.shutdown();
    }
}
