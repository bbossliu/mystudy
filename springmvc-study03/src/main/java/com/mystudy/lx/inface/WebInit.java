package com.mystudy.lx.inface;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author dalaoban
 * @create 2020-09-14-10:00
 */
public interface WebInit {
    void start(ServletContext var1) throws ServletException;
}
