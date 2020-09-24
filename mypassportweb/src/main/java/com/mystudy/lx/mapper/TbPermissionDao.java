package com.mystudy.lx.mapper;

import com.mystudy.lx.entity.TbPermission;

import java.util.List;

/**
 * 权限表(TbPermission)表数据库访问层
 *
 * @author makejava
 * @since 2020-02-13 15:46:38
 */
public interface TbPermissionDao {

    List<TbPermission> permissionByUserId(Long uid);

}