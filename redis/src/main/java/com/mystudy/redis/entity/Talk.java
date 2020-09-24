package com.mystudy.redis.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author dalaoban
 * @create 2020-08-19-10:45
 */
@Getter
@Setter
@ToString
public class Talk {
    private String id;  //朋友圈id
    private Integer likeUserId;  //点赞的用户id
    private boolean status;  //状态   是点赞 还是取消点赞
}