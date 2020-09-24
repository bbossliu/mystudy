package com.mystudy.lx.nio03.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author dalaoban
 * @create 2020-06-17-23:16
 */
public class ChatClient {
    public static void main(String[] args) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            //切换到非阻塞模式
            socketChannel.configureBlocking(false);

            Selector selector = Selector.open();
            //在客户端的选择器上，注册一个通道，并标识该通道所感兴趣的事件是向服务器发送连接（连接就绪）
            socketChannel.register(selector,SelectionKey.OP_CONNECT);

            //随机连接到服务端提供的一个端口上
            int[] ports = {7777,8888,9999};
            int port = ports[(int)Math.random()*3];

            socketChannel.connect(new InetSocketAddress(port));

            while (true){
                //一直阻塞，直到选择器上存在已经就绪的通道
                selector.select();

                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()){
                    SelectionKey selectionKey = iterator.next();

                    if(selectionKey.isConnectable()) {
                        ByteBuffer sendBuffer = ByteBuffer.allocate(1024);

                        //创建一个用于和服务端交互的Channel

                       SocketChannel client = (SocketChannel) selectionKey.channel();

                       //如果状态是正在连接中
                       if(client.isConnectionPending()){
                           boolean isConnected = client.finishConnect();

                           if(isConnected){
                               System.out.println("连接成功！访问的端口是："+port);
                               //向服务端发送一条测试消息
                               sendBuffer.put("connectting".getBytes());

                               sendBuffer.flip();

                               client.write(sendBuffer);
                           }

                           new Thread(()->{
                               while (true){
                                   try {
                                       sendBuffer.clear();

                                       //接受用戶从控制台输入的内容，并发送给服务端
                                       InputStreamReader reader = new InputStreamReader(System.in);
                                       BufferedReader bufferedReader = new BufferedReader(reader);
                                       String message = bufferedReader.readLine();

                                       sendBuffer.put(message.getBytes());
                                       sendBuffer.flip();
                                       client.write(sendBuffer);
                                   }catch (Exception e){
                                       e.printStackTrace();
                                   }
                               }
                           }).start();
                       }
                       //标记通道感兴趣的事件是读取服务端消息（读就绪）
                       client.register(selector,SelectionKey.OP_READ);
                    }else if(selectionKey.isReadable()){

                        SocketChannel client = (SocketChannel) selectionKey.channel();

                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);

                        //将服务端的反馈消息放入readBuffer中
                        int len = client.read(readBuffer);

                        if(len>0){
                            String receive = new String(readBuffer.array(), 0, len);
                            System.out.println(receive);
                        }
                    }
                }
                selectionKeys.clear();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
