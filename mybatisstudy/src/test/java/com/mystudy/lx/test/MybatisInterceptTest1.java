package com.mystudy.lx.test;

import com.mystudy.lx.entity.MybatisStudent;
import com.mystudy.lx.mapper.MybatisStudentMapper;
import org.assertj.core.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dalaoban
 * @create 2020-05-17-23:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan(basePackages = "com.mystudy.lx.mapper")
public class MybatisInterceptTest1 {

    @Resource
    MybatisStudentMapper studentMapper;

    @Test
    public void test1(){
        List<MybatisStudent> mybatisStudents = studentMapper.selectList(null);
        mybatisStudents.forEach(System.out::println);
    }
}
