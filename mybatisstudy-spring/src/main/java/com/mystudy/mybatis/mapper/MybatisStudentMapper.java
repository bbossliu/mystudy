package com.mystudy.mybatis.mapper;

import com.mystudy.mybatis.entity.MybatisStudent;

/**
 * @author dalaoban
 * @create 2020-05-24-14:32
 */
public interface MybatisStudentMapper {

    MybatisStudent selectMyStudent(Long id);
}
