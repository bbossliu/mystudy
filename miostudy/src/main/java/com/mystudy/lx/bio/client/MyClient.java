package com.mystudy.lx.bio.client;

import java.io.*;
import java.net.Socket;

/**
 * @author dalaoban
 * @create 2020-06-14-11:04
 */
public class MyClient {

    public static void main(String[] args) {
        //客户端连接上服务端
        OutputStream outputStream=null;
        InputStream inputStream =null;
        Socket socket =null;
        try {
            socket = new Socket("127.0.0.1", 8888);

            //用于接收服务端发来的文件，并将文件内容保存在内存中
            inputStream = socket.getInputStream();

            outputStream = new FileOutputStream("D:\\client.jpg");

            byte[] bytes=new byte[64];
            int len=-1;
            while ((len=inputStream.read(bytes))!=-1){
                outputStream.write(bytes,0,len);
            }
            System.out.println("文件接收成功");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(outputStream!=null){
                    outputStream.close();
                }
                if(inputStream!=null){
                    inputStream.close();
                }
                if(socket!=null){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
