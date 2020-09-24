package com.mystudy.lx.proxy.staticp;

/**
 * @author dalaoban
 * @create 2020-06-27-11:05
 */
public class TargetSubject implements Subject {

    /**
     * 场景李顺要李云带他去买书
     */
    @Override
    public void buyBook() {
        System.out.println("李顺要买书");
    }
}
