package com.mystudy.lx;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author dalaoban
 * @create 2020-06-21-16:13
 */
public class Swimmer implements Delayed {

    private String name;

    //这里的结束时间需要是毫秒---this.endTime-System.currentTimeMillis();
    private long endTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public Swimmer(String name, long endTime) {
        this.name = name;
        this.endTime = endTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return this.endTime-System.currentTimeMillis();
    }

    //根据剩余时间大小进行排序
    @Override
    public int compareTo(Delayed delayed) {
        Swimmer swimmer = (Swimmer)delayed;

        return this.getDelay(TimeUnit.SECONDS)-swimmer.getDelay(TimeUnit.SECONDS) > 0 ? 1:0;
    }
}
