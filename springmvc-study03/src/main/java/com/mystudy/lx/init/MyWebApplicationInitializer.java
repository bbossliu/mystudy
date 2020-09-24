package com.mystudy.lx.init;

import com.mystudy.lx.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author dalaoban
 * @create 2020-09-14-9:49
 */
//public class MyWebApplicationInitializer implements WebApplicationInitializer {
//    /**
//     * @HandlesTypes(WebApplicationInitializer.class)
//     * @param servletContext
//     * @throws ServletException
//     */
//    @Override
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
//        context.register(AppConfig.class);
//        context.setServletContext(servletContext);
//        context.refresh();
//
//        DispatcherServlet servlet = new DispatcherServlet(context);
//        ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
//        registration.setLoadOnStartup(1);
//        registration.addMapping("*.do");
//    }
//}
