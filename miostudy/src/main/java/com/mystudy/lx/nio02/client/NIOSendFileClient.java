package com.mystudy.lx.nio02.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author dalaoban
 * @create 2020-06-14-22:43
 */
public class NIOSendFileClient {

    public static void main(String[] args) {
        SocketChannel socketChannel = null;
        FileChannel inputChannel = null;
        try {
            socketChannel = SocketChannel.open(new InetSocketAddress(8888));

            inputChannel = FileChannel.open(Paths.get("D:\\t1.zip"), StandardOpenOption.READ);

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            long start = System.currentTimeMillis();
            while (inputChannel.read(buffer)!=-1){
                //写-》读
                buffer.flip();

                socketChannel.write(buffer);
                //读-》写
                buffer.clear();
            }
            long end = System.currentTimeMillis();
            System.out.println("客户端发送文件所耗费时间："+(end-start));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
           try {
               if(inputChannel!=null){
                   inputChannel.close();
               }

               if(socketChannel!=null){
                   socketChannel.close();
               }
           }catch (Exception e){
               e.printStackTrace();
           }
        }
    }
}
