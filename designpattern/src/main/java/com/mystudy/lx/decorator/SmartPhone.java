package com.mystudy.lx.decorator;

/**
 * @author dalaoban
 * @create 2020-06-14-10:38
 */
//装饰角色
public class SmartPhone implements Phone {
    private Phone phone;


    public SmartPhone(Phone phone){
        this.phone=phone;
    }

    @Override
    public void call() {
        phone.call();
    }
}
