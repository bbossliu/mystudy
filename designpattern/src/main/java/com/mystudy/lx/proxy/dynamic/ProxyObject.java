package com.mystudy.lx.proxy.dynamic;

import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * @author dalaoban
 * @create 2020-06-27-11:15
 */
public class ProxyObject {

    public static Object proxyObj(){
        //代理对象的类加载器
        //接口
        //实现代理的类
        return Proxy.newProxyInstance(ProxyDynamic.class.getClassLoader(),new SujectDynamicImpl().getClass().getInterfaces(),new ProxyDynamic(new SujectDynamicImpl()));
    }
}
