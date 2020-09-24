package com.mystudy.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dalaoban
 * @since 2020-05-21 22:44:57
 */
@TableName(value = "mybatis_student")
public class MybatisStudent extends Model<MybatisStudent> implements Serializable {
    private static final long serialVersionUID = -68628533967968458L;
    /**
    * 主键
    */
    @TableId(type = IdType.AUTO)
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

    @Version
    private Integer version;


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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "MybatisStudent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", version=" + version +
                '}';
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }


}