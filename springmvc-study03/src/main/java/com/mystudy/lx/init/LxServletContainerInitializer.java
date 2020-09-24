package com.mystudy.lx.init;

import com.mystudy.lx.inface.WebInit;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author dalaoban
 * @create 2020-09-14-10:01
 */
@HandlesTypes({WebInit.class})
public class LxServletContainerInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> webInitClasses, ServletContext ctx) throws ServletException {
        LinkedList<WebInit> webInits = new LinkedList<>();

        if(webInitClasses != null){
            for (Class<?> webInitClass : webInitClasses) {
                try {
                    WebInit webInit =  (WebInit) webInitClass.newInstance();
                    webInits.add(webInit);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        for (WebInit webInit : webInits) {
            webInit.start(ctx);
        }
    }
}
