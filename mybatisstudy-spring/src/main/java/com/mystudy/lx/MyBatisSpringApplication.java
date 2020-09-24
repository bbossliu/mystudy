package com.mystudy.lx;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.alibaba.druid.pool.WrapperAdapter;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.api.Assert;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.mystudy.lx.config.MySpringConfiguration;
import com.mystudy.lx.entity.MybatisStudent;
import com.mystudy.lx.mapper.MybatisStudentMapper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.Date;

/**
 * @author dalaoban
 * @create 2020-05-24-14:06
 */
public class MyBatisSpringApplication {

    public static void main(String[] args) throws SQLException {

//       testMp();
        testAR();
    }


    public static void testMp() throws SQLException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MySpringConfiguration.class);

        OptimisticLockerInterceptor optimisticLockerInterceptor = context.getBean("optimisticLockerInterceptor", OptimisticLockerInterceptor.class);

        DruidDataSource druidDataSource=(DruidDataSource)context.getBean("dataSource");
        DruidPooledConnection connection = druidDataSource.getConnection();
        System.out.println(connection);

        MybatisStudentMapper myStudentMapper = context.getBean("mybatisStudentMapper", MybatisStudentMapper.class);
        MybatisStudent mybatisStudent = new MybatisStudent();

        mybatisStudent.setUpdateTime(new Date());
        mybatisStudent.setVersion(1);
        mybatisStudent.setName("网易");
        myStudentMapper.insert(mybatisStudent);


        Long id = mybatisStudent.getId();

        MybatisStudent et = new MybatisStudent();
        et.setId(1264448023224668175L);
        et.setName("yunn");
        et.setVersion(1);

        myStudentMapper.updateById(et);
//        Assert.assertEquals("Should update success", 1, myStudentMapper.updateById(mybatisStudent1));
//        Assert.assertEquals("Should version = version+1", 2, mybatisStudent1.getVersion().intValue());


    }

    public static void testAR(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MySpringConfiguration.class);
//        MyStudentMapper myStudentMapper = context.getBean("myStudentMapper", MyStudentMapper.class);

//        MybatisStudent mybatisStudent = new MybatisStudent();
//        mybatisStudent.setName("王云");
//        mybatisStudent.setVersion(0);
//        mybatisStudent.setId(1264448023224668161L);
//        mybatisStudent.updateById();
        MybatisStudentMapper mybatisStudentMapper = context.getBean("mybatisStudentMapper", MybatisStudentMapper.class);
        mybatisStudentMapper.selectById(2);
    }






}
