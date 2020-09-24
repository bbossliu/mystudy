package com.mystudy.lx.decorator;

/**
 * @author dalaoban
 * @create 2020-06-14-10:43
 */
public class Test {

    public static void main(String[] args) {
        AISmartPhone aiSmartPhone = new AISmartPhone(new BasePhone());
        aiSmartPhone.call();

        AutoSizeSmartPhone autoSizeSmartPhone = new AutoSizeSmartPhone(new BasePhone());
        autoSizeSmartPhone.call();
    }
}
