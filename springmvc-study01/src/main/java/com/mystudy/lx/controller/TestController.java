package com.mystudy.lx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author dalaoban
 * @create 2020-09-10-13:42
 */
@Controller
public class TestController {

    @ResponseBody
    @RequestMapping("testController")
    public String testController(){
        return "testController";
    }
}
