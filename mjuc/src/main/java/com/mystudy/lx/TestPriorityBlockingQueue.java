package com.mystudy.lx;

import com.mystudy.lx.entity.MyJob;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author dalaoban
 * @create 2020-06-19-23:05
 */
public class TestPriorityBlockingQueue {


    public static void main(String[] args) {
        PriorityBlockingQueue<MyJob> myJobs = new PriorityBlockingQueue<>();

        myJobs.add(new MyJob(3));
        myJobs.add(new MyJob(2));
        myJobs.add(new MyJob(1));

        try {
            System.out.println("队列元素："+myJobs);

            System.out.println("队列排头元素："+myJobs.take());

            System.out.println("出队后剩余元素："+myJobs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
