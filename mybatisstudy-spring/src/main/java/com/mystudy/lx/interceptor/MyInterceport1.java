package com.mystudy.lx.interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;

import java.lang.reflect.Method;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author dalaoban
 * @create 2020-05-30-10:49
 */
@Intercepts({
        @Signature(type = StatementHandler.class,method = "query",args = {Statement.class, ResultHandler.class}),
        @Signature(type = StatementHandler.class,method = "parameterize",args = {Statement.class})
})
public class MyInterceport1 implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object target = invocation.getTarget();
        Method method = invocation.getMethod();
        Object[] args = invocation.getArgs();
        System.out.println("目标对象"+target);
        System.out.println("目标方法"+method);
        System.out.println("目标参数"+args);

        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object value = metaObject.getValue("parameterHandler.parameterObject");

        metaObject.setValue("parameterHandler.parameterObject",3);
        Object value1 = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println(value1);
        Object proceed = invocation.proceed();
        System.out.println("执行拦截器"+proceed);
        return proceed;
    }

    @Override
    public Object plugin(Object target) {

        Object wrap = Plugin.wrap(target, this);
        System.out.println("调用plug方法"+wrap);
        return wrap;
    }

    @Override
    public void setProperties(Properties properties) {
        System.out.println("-------------------------------设置属性");
        System.out.println(properties);
    }
}
