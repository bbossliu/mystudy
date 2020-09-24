package com.mystudy.lx.mapper;

import com.mystudy.lx.entity.TbUser;

import java.util.List;

/**
 * 用户表(TbUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-13 15:06:36
 */
public interface TbUserDao {

    /**
     * 通过实体作为筛选条件查询
     *
     * @param tbUser 实例对象
     * @return 对象列表
     */
    List<TbUser> queryAll(TbUser tbUser);

}