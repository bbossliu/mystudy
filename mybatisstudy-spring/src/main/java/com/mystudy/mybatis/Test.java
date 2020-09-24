package com.mystudy.mybatis;

import com.mystudy.mybatis.entity.MybatisStudent;
import com.mystudy.mybatis.mapper.MybatisStudentMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * @author dalaoban
 * @create 2020-05-24-22:18
 */
public class Test {

    public static void main(String[] args) throws IOException {
        Reader reader = Resources.getResourceAsReader("mybatis.xml");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);


        SqlSession sqlSession = sqlSessionFactory.openSession();

        MybatisStudentMapper mapper = sqlSession.getMapper(MybatisStudentMapper.class);

        MybatisStudent mybatisStudent = mapper.selectMyStudent(2L);

        System.out.println(mybatisStudent);
    }
}
