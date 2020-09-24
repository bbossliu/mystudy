package com.mystudy.lx.controller;

import com.mystudy.lx.service.MybatisStudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (MybatisStudent)表控制层
 *
 * @author makejava
 * @since 2020-05-21 22:45:02
 */
@RestController
@RequestMapping("mybatisStudent")
public class MybatisStudentController {
    /**
     * 服务对象
     */
    @Resource
    private MybatisStudentService mybatisStudentService;


}