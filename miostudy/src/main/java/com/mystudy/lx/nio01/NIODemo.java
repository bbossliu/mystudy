package com.mystudy.lx.nio01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author dalaoban
 * @create 2020-06-14-18:00
 */
public class NIODemo {

    public static void main(String[] args) {
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        FileChannel inChannel=null;
        FileChannel outChannel =null;
        try {
            inputStream = new FileInputStream("D:\\t1.zip");
            outputStream = new FileOutputStream("D:\\t2.zip");

            //输入通道
            inChannel = inputStream.getChannel();
            //输出通道
            outChannel = outputStream.getChannel();

            //字节缓冲区
            ByteBuffer buffer = ByteBuffer.allocateDirect(64);
            long start = System.currentTimeMillis();
            while (inChannel.read(buffer)!=-1){
                //将写变成读
                buffer.flip();
                outChannel.write(buffer);
                //将读变成写
                buffer.clear();
            }
            long end = System.currentTimeMillis();

            System.out.println("所用时间："+(end-start));
        } catch (Exception e ) {
            e.printStackTrace();
        }finally {

                try {
                    if(outChannel!=null) {
                        outChannel.close();
                    }
                    if(inChannel!=null){
                        inChannel.close();
                    }
                    if(outputStream!=null){
                        outputStream.close();
                    }
                    if(inputStream!=null){
                        inputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
