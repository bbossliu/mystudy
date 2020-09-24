package com.mystudy.lx;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author dalaoban
 * @create 2020-06-19-22:38
 */
public class TestReadWriteLock {

    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        TestReadWriteLock myRWLock = new TestReadWriteLock();

        new Thread(()->{
            //读操作
            myRWLock.myRead(Thread.currentThread());

            //写操作
            myRWLock.myWrite(Thread.currentThread());
        },"线程1").start();


        new Thread(()->{
            //读操作
            myRWLock.myRead(Thread.currentThread());

            //写操作
            myRWLock.myWrite(Thread.currentThread());
        },"线程2").start();
    }


    //模拟共享资源的读操作
    public void myRead(Thread thread){
        rwl.readLock().lock();

        try {
            for(int i=0;i<10000;i++){
                System.out.println(Thread.currentThread().getName()+"正在读取："+i);
            }
            System.out.println(Thread.currentThread().getName()+"--读取完毕==========");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwl.readLock().unlock();
        }
    }

    //模拟共享资源的读操作
    public void myWrite(Thread thread){
        rwl.writeLock().lock();
        try {
            for(int i=0;i<10000;i++){
                System.out.println(Thread.currentThread().getName()+"正在写入："+i);
            }
            System.out.println(Thread.currentThread().getName()+"--写入完毕+++++++++++++");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwl.writeLock().unlock();
        }
    }
}
