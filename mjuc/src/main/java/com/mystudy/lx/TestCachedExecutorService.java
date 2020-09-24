package com.mystudy.lx;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author dalaoban
 * @create 2020-06-21-17:49
 */
public class TestCachedExecutorService {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i=0;i<3;i++){
            executorService.submit(()->{
                System.out.println(Thread.currentThread().getName());
            },"线程"+i);
        }

        executorService.shutdown();

    }
}
