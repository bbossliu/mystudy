package com.mystudy.lx.nio01;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author dalaoban
 * @create 2020-06-14-18:57
 */
public class MappedByteBufferDemo {

    public static void main(String[] args) {
        FileChannel inputChannel = null;
        FileChannel outputChannel = null;
        try {
            long start = System.currentTimeMillis();
            inputChannel = FileChannel.open(Paths.get("D://t1.zip"), StandardOpenOption.READ);

            outputChannel = FileChannel.open(Paths.get("D://t3.zip"),StandardOpenOption.WRITE,StandardOpenOption.READ,StandardOpenOption.CREATE);

            //获取一个物理映射文件对象
            MappedByteBuffer inputMapBuffer = inputChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputChannel.size());

            MappedByteBuffer outputMapBuffer = outputChannel.map(FileChannel.MapMode.READ_WRITE, 0, inputChannel.size());

           byte[] dst = new byte[inputMapBuffer.limit()];

           inputMapBuffer.get(dst);

           outputMapBuffer.put(dst);

            long end = System.currentTimeMillis();

            System.out.println("所用时间："+(end-start));

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(outputChannel!=null){
                    outputChannel.close();
                }
                if(inputChannel!=null){
                    inputChannel.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

}
