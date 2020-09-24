package com.mystudy.lx.nio01;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author dalaoban
 * @create 2020-06-14-19:11
 */
public class MappedByteBufferDemo02 {

    /**
     * 示例：直接缓冲区的内存映射文件MappedByteBuffer，本身代表磁盘上对应的物理文件，如果
     * 直接修改MappedByteBuffer,磁盘上的物理文件也会随之修改
     * @param args
     */
    public static void main(String[] args) {
        RandomAccessFile raf = null ;
        try {
            raf = new RandomAccessFile("D://2.txt","rw");

            FileChannel channel = raf.getChannel();

            MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, raf.length());

            map.put(1,(byte)'Y');

            map.put(3,(byte)'X');
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(raf!=null){
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
