package com.mystudy.lx.nio03;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.concurrent.TimeUnit;

/**
 * @author dalaoban
 * @create 2020-06-15-23:05
 */
public class FileLockDemo {

    public static void main(String[] args) {
        try {
            RandomAccessFile raf = new RandomAccessFile("E:\\hello.txt","rw");

            FileChannel inputChannel = raf.getChannel();

            //将该文件2-4的位置加锁，并且是共享锁，读共享
            FileLock lock = inputChannel.lock(2, 4, false);

            System.out.println("main线程锁住该资源2-4的位置3秒：");


            new Thread(()->{
               byte[] b = new byte[20];

                try {
                    raf.read(b,0,8);
//                    raf.write("uuuuuuuu".getBytes(),0,8);
                    System.out.println("打印："+new String(b,0,b.length));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            TimeUnit.SECONDS.sleep(3);
            System.out.println("3秒结束，释放该锁");
            lock.release();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
