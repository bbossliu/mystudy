package com.mystudy.jdk7;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dalaoban
 * @create 2020-07-19-22:44
 */
public class HashMapTest {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("k1", "v1");
        map.get("");

        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("hlleo","hello");


        ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException = new ArrayIndexOutOfBoundsException();
        ArithmeticException arithmeticException = new ArithmeticException();
        ClassCastException classCastException = new ClassCastException();
        ClassNotFoundException classNotFoundException = new ClassNotFoundException();
        ConcurrentModificationException concurrentModificationException = new ConcurrentModificationException();
        FileNotFoundException fileNotFoundException = new FileNotFoundException();
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException();


        /**
         * 该方法用于返回一个小于等于i的一个2的幂次方数
         * 采用的的方法为最高为 1
         */
    }
}
