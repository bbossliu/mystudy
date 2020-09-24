package com.mystudy.lx.bio.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author dalaoban
 * @create 2020-06-14-10:52
 */
public class MyServer {

    public static void main(String[] args) {
        try {
            //创建一个ServerSocket对象
            ServerSocket serverSocket = new ServerSocket(8888);
            while (true){
                //等待客户端发来连接，一个socket对象即一个连接
                Socket socket = serverSocket.accept();

                new Thread(()->{
                    OutputStream outputStream=null;
                    FileInputStream inputStream=null;
                    try {

                        outputStream = socket.getOutputStream();

                        //获取一个文件对象
                        File file = new File("D:\\1.jpg");
                        //转换成输入流对象，即将该文件读入内存中
                        inputStream = new FileInputStream(file);

                        byte[] bytes=new byte[64];
                        int len=-1;
                        while ((len=inputStream.read(bytes))!=-1){
                            outputStream.write(bytes,0,len);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }finally {
                        if(inputStream!=null){
                            try {
                                inputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if(outputStream!=null){
                            try {
                                outputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if(socket!=null){
                            try {
                                socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
