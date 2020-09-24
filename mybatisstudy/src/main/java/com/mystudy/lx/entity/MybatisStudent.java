package com.mystudy.lx.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (MybatisStudent)实体类
 *
 * @author makejava
 * @since 2020-05-21 22:44:57
 */
public class MybatisStudent implements Serializable {
    private static final long serialVersionUID = -68628533967968458L;
    /**
    * 主键
    */
    private Long id;
    /**
    * 姓名
    */
    private String name;
    /**
    * 性别（0='男',1='女')
    */
    private String sex;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 修改时间
    */
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    @Override
    public String toString() {
        return "MybatisStudent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}