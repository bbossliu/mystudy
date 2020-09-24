package com.mystudy.lx.nio02.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author dalaoban
 * @create 2020-06-14-22:57
 */
public class NIOSendFileDirectClient {

    public static void main(String[] args) {
        SocketChannel socketChannel = null;
        FileChannel inputChannel = null;
        try {
            long start = System.currentTimeMillis();
            socketChannel = SocketChannel.open(new InetSocketAddress(8888));

            inputChannel = FileChannel.open(Paths.get("D:\\t1.zip"), StandardOpenOption.READ);
            System.out.println(inputChannel.size());
            //从而在内存地址中开辟与直接缓冲区一样的内存地址大小
//            inputChannel.transferTo(0,inputChannel.size(),socketChannel);

            MappedByteBuffer inputMap = inputChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputChannel.size());

            byte[] dst = new byte[inputMap.limit()];

            inputMap.get(dst);
            ByteBuffer buffer = ByteBuffer.allocateDirect(dst.length);
            buffer.put(dst);
            System.out.println(buffer.capacity());
            socketChannel.write( buffer );

            long end = System.currentTimeMillis();

            System.out.println("发送文件所花费时间为："+(end-start));
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
