package com.mystudy.lx.proxy.dynamic;

/**
 * @author dalaoban
 * @create 2020-06-27-11:21
 */
public class TestDynamic {
    public static void main(String[] args) {
        SubjectDynamic sujectDynamic = (SubjectDynamic)ProxyObject.proxyObj();
        System.out.println("由我代理：");
        sujectDynamic.buyBook();
        System.out.println("执行结束");

    }
}
