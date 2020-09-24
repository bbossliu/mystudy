package com.mystudy.redis.template;

/**
 * @author dalaoban
 * @create 2020-08-19-10:50
 */

public interface CacheLoadble<T> {
    T load();
}
