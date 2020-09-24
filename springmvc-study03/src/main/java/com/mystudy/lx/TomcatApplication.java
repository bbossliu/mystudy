package com.mystudy.lx;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.catalina.startup.Tomcat;

/**
 * @author dalaoban
 * @create 2020-09-14-10:17
 */
public class TomcatApplication {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(80);
        System.out.println("==============="+TomcatApplication.class.getResource("/").getPath().replaceAll("%20", " "));
        Context context = tomcat.addContext("/", System.getProperty("java.io.tmpdir"));

        tomcat.addServlet(context,"default",new DefaultServlet());

        //添加解码映射
//        context.addServletMappingDecoded("/*","default");

        context.addLifecycleListener((LifecycleListener) Class.forName(tomcat.getHost().getConfigClass()).newInstance());

        tomcat.start();

        tomcat.getServer().await();



    }
}
