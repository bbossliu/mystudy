package com.mystudy.lx;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author dalaoban
 * @create 2020-06-26-21:38
 */
public class TestThreadPoolExecutor {

    public static void main(String[] args) {



        /**
         * 核心线程
         * 最大线程数
         * 非核心线程空闲等待时间
         * 等待队列
         * 拒绝策略
         */
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 2,
                10L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3), new MyReject());

        for (int i = 1; i <=6; i++) {
            poolExecutor.execute(new MyThread02("t"+i));
            System.out.println("启动-"+i+"-个线程此时：");
            System.out.println("此时等待队列："+poolExecutor.getQueue().size());
            System.out.println("此时工作窗口："+poolExecutor.getActiveCount());
            System.out.println("---");

        }
        poolExecutor.shutdown();

    }
}

class MyThread02 implements Runnable{

    /**
     * 线程名字
     */
    private String name ;

    public MyThread02() {
    }

    public MyThread02(String name) {
        this.name = name;
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
            System.out.println("线程"+name+"-"+"启动");
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class MyReject implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("拒绝服务的线程名称为："+((MyThread02) r).getName());
    }
}
