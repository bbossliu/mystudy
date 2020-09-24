package com.mystudy.lx.controller;

import com.mystudy.lx.annotation.MyController;
import com.mystudy.lx.annotation.MyRequestMapping;
import com.mystudy.lx.annotation.MyResponseBody;
import com.mystudy.lx.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dalaoban
 * @create 2020-09-12-21:48
 */
@MyController
@MyRequestMapping("/test")
public class TestController {

    @MyRequestMapping("/test.do")
    @MyResponseBody
    public Object test(String name1, HttpServletRequest request, HttpServletResponse response, UserEntity userEntity){
        request.getParameter("name");
        System.out.println(name1);
        System.out.println(request);
        System.out.println(response);
        System.out.println(userEntity);
        return  name1+"--"+userEntity;

    }


    @MyRequestMapping("/model.do")
    public Object model(){

        return "index";
    }


}
