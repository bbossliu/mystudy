package com.mystudy.redis.util;

/**
 * @author dalaoban
 * @create 2020-08-19-10:52
 */
public interface Lock {
    void lock(String key);
    boolean tryLock(String key);
    void unlock(String key) throws Exception;
}