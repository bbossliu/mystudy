package com.mystudy.lx.proxy.staticp;

/**
 * @author dalaoban
 * @create 2020-06-27-11:07
 */
public class ProxySubject implements Subject {

    TargetSubject target;

    @Override
    public void buyBook() {
        target = new TargetSubject();
        target.buyBook();
    }
}
