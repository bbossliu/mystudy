package com.lx.generotor.hell.entity;

import 你自己的父类实体,没有就不用设置!;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-05-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class MybatisStudent extends 你自己的父类实体,没有就不用设置! {

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
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;


}
