package com.mystudy.lx.entity;

/**
 * @author dalaoban
 * @create 2020-06-19-23:05
 */
public class MyJob implements Comparable<MyJob> {

    private int id;

    public MyJob(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(MyJob o) {
        return this.id > o.id ? 1 : (this.id<o.id ? -1 : 0);
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }
}
