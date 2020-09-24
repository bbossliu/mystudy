package com.mystudy.lx;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author dalaoban
 * @create 2020-06-26-22:18
 */
public class TestCompletableFuture {

    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> taskList = new CopyOnWriteArrayList<>();
        taskList.add(1);
        taskList.add(2);
        taskList.add(3);
        taskList.add(4);

        // 结果集
        List<Character> resultList = new ArrayList<>();

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        CompletableFuture[] completableFutures = taskList.stream()
                .map(integer -> CompletableFuture.supplyAsync(() -> calcASCII(integer), executorService)
                        .thenApply((i) -> {
                            char c = (char) i.intValue();
                            return c;
                        }).whenComplete((ch, e) -> {
                            resultList.add(ch);
                            executorService.shutdown();
                        })).toArray(CompletableFuture[]::new);

        // 封装后无返回值，必须自己whenComplete()获取
        CompletableFuture.allOf(completableFutures).join();//future.get()

        System.out.println("完成！result=" + resultList);
    }

    //计算i的ASCII值
    public static Integer calcASCII(Integer i) {
        try {
            if (i == 1) {
                Thread.sleep(5000);
            } else {
                Thread.sleep(1000);
            }
            //数字 -> A-D对应的ascii
            i = i + 64;
            System.out.println("【阶段1】线程" + Thread.currentThread().getName()
                    + "执行完毕，" + "已将" + i
                    + "转为了A(或B或C或D)对应的ASCII" + i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return i;
    }
}
