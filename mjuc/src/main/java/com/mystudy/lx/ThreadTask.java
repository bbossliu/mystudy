package com.mystudy.lx;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author dalaoban
 * @create 2020-06-21-18:48
 */
public class ThreadTask implements Callable<String> {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {

        //获取当前线程的名字
        String name = Thread.currentThread().getName();
        long start = System.currentTimeMillis();
        System.out.println(name+"-启动时间-"+start);
        //模拟线程执行
        TimeUnit.SECONDS.sleep(new Random(2).nextLong());

        System.out.println(name+"-线程正在执行");
        return name;
    }


}
