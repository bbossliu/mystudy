package com.mystudy.lx;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author dalaoban
 * @create 2020-06-21-18:54
 */
public class TestScheduledThreadPool  {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());

        List<Future> futures = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Future<String> future = scheduledExecutorService.schedule(new ThreadTask(), new Random(10).nextInt(), TimeUnit.SECONDS);
            futures.add(future);
        }

        //打印结果
        for (Future<String> future : futures) {

            try {
                System.out.println(future.get()+(future.isDone()?"已完成":"未完成"));
                System.out.println("等待线程结束后线程的返回结果："+future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        scheduledExecutorService.shutdown();
    }
}
