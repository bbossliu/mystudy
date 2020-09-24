package com.mystudy.lx.decorator;

/**
 * @author dalaoban
 * @create 2020-06-14-10:37
 */
//具体的构件角色
public class BasePhone implements Phone {

    @Override
    public void call() {
        System.out.println("我有打电话的功能");
    }
}
