package com.mystudy.lx.nio02.server;

import javax.print.attribute.standard.Finishings;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author dalaoban
 * @create 2020-06-14-22:27
 */
public class NIOSendFileServer {

    public static void main(String[] args) {
        ServerSocketChannel serverSocketChannel = null;
        FileChannel outputChannel = null;
        SocketChannel socketChannel = null;
        try {

            serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.bind(new InetSocketAddress(8888));

//            outputStream = new FileOutputStream("D:\\t8.zip");
//
//            outputChannel = outputStream.getChannel();

            outputChannel = FileChannel.open(Paths.get("D:\\t10.zip"), StandardOpenOption.WRITE,StandardOpenOption.CREATE);

            socketChannel = serverSocketChannel.accept();
            System.out.println("客户端："+socketChannel.getLocalAddress()+"-连接成功");

            long start = System.currentTimeMillis();
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (socketChannel.read(buffer)!=-1){
                buffer.flip();
                outputChannel.write(buffer);
                buffer.clear();
            }

            long end = System.currentTimeMillis();

            System.out.println("接收文件总共花费时间："+(end-start));
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(outputChannel!=null){
                    outputChannel.close();
                }

                if(socketChannel!=null){
                    socketChannel.close();
                }

                if(serverSocketChannel!=null){
                    serverSocketChannel.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
