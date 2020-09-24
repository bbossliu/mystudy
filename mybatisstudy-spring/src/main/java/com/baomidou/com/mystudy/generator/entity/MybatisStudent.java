package com.baomidou.com.mystudy.generator.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author dalaoban
 * @since 2020-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MybatisStudent implements Serializable {

    private static final long serialVersionUID=1L;

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


}
