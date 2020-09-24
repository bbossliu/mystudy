package com.mystudy.lx.init;

import com.mystudy.lx.config.AppConfig;
import com.mystudy.lx.inface.WebInit;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * @author dalaoban
 * @create 2020-09-14-10:06
 */
public class LxWebApplicationInitializer implements WebInit {
    @Override
    public void start(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        ac.register(AppConfig.class);
        ac.setServletContext(servletContext);
        ac.refresh();

        DispatcherServlet dispatcherServlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic registration = servletContext.addServlet("app", dispatcherServlet);
        registration.addMapping("*.do");
        registration.setLoadOnStartup(1);

    }
}
