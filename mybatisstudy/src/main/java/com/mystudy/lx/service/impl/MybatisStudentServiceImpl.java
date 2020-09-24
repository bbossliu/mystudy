package com.mystudy.lx.service.impl;

import com.mystudy.lx.mapper.MybatisStudentMapper;
import com.mystudy.lx.service.MybatisStudentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (MybatisStudent)表服务实现类
 *
 * @author makejava
 * @since 2020-05-21 22:45:02
 */
@Service("mybatisStudentService")
public class MybatisStudentServiceImpl implements MybatisStudentService {
    @Resource
    private MybatisStudentMapper mybatisStudentDao;

}