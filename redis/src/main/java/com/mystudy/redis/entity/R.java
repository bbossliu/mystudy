package com.mystudy.redis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author dalaoban
 * @create 2020-08-19-10:44
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class R {
    private Integer code;
    private Object data;
    private String msg;
}
