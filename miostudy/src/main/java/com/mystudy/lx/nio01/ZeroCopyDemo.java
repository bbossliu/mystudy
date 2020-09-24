package com.mystudy.lx.nio01;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author dalaoban
 * @create 2020-06-14-19:20
 */
public class ZeroCopyDemo {
    public static void main(String[] args) {
        FileChannel inputChannel = null;

        FileChannel outputChannel = null;
        try {
            long start = System.currentTimeMillis();
            inputChannel = FileChannel.open(Paths.get("D://t1.zip"), StandardOpenOption.READ);

            outputChannel = FileChannel.open(Paths.get("D://t5.zip"),StandardOpenOption.WRITE,StandardOpenOption.CREATE);

            inputChannel.transferTo(0,inputChannel.size(),outputChannel);

            /**
             * or   outputChannel.transferFrom(inputChannel,0,inputChannel.size());
             */

            long end = System.currentTimeMillis();

            System.out.println("所花费时间为："+(end-start));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputChannel!=null){
                try {
                    outputChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputChannel!=null){
                try {
                    inputChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
