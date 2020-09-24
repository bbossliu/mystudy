package com.mystudy.lx;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author dalaoban
 * @create 2020-06-21-17:52
 */
public class TestFixedExecutorService {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        List<Future<Integer>> futures = new ArrayList<>();

        for (int i = 0; i <6; i++) {

            Future<Integer> future = executorService.submit(() -> {
                int sum = 0;
                for (int j = 1; j <=10; j++) {
                    sum+=j;
                }
                return sum;
            });
            futures.add(future);
        }

        futures.stream().forEach(future->{
            Integer integer = null;
            try {
                integer = future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(integer);
        });

        executorService.shutdown();
    }
}
