package com.mystudy.lx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author dalaoban
 * @create 2020-09-14-9:55
 */
@Controller
public class MyTestController {

    @RequestMapping("test")
    @ResponseBody
    public String hello(){
        return "test";
    }
}
