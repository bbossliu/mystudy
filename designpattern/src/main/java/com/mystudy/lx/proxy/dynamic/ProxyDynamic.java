package com.mystudy.lx.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author dalaoban
 * @create 2020-06-27-11:10
 */
public class ProxyDynamic implements InvocationHandler {

    //代理对象
    private Object obj;


    public ProxyDynamic(Object obj) {
        this.obj = obj;
    }

    /**
     *
     * @param proxy 代理对象
     * @param method 需要代理调用的方法
     * @param args 方法参数类型
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(obj,args);
    }
}
