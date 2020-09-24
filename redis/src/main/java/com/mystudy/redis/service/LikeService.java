package com.mystudy.redis.service;

import com.mystudy.redis.entity.Talk;

public interface LikeService {

    boolean likeAndCancelLike(Talk talk);


    long getLikeCount(Talk talk);


    boolean isLike(Talk talk);

}
