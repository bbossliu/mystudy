package com.mystudy.lx.nio03.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author dalaoban
 * @create 2020-06-17-23:16
 */
public class ChatServer {

    /**
     * clientsMap 用于保存客户端
     * key 客户端的名字
     * value 客户端连接服务器的Channel
     */
    private static Map<String, SocketChannel>  clientsMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
       int[] ports = new int[]{7777,8888,999};

        Selector selector = Selector.open();

        for(int port : ports){
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            //设置为非阻塞模式
            serverSocketChannel.configureBlocking(false);

            ServerSocket serverSocket = serverSocketChannel.socket();

            //将聊天服务绑定到7777,8888,9999三个端口上
            serverSocket.bind(new InetSocketAddress(port));
            System.out.println("服务端启动成功，端口:"+port);

            //在服务端的选择器上，注册一个通道，并标识该通道所感兴趣的事件是接收客户端的连接（接收就绪）
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        }

        while (true){

            //一直阻塞，直到选择器上存在已经就绪的通道（包含已就绪的事件）
            selector.select();

            //拿到选择器上所有的感兴趣事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()){

                SelectionKey selectionKey = iterator.next();
                SocketChannel clientChannel = null;
                //如果是可以接收就绪状态
                if(selectionKey.isAcceptable()){

                    //拿到之前注册的ServerSocketChannel
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();

                    clientChannel = serverSocketChannel.accept();

                    //设置为非阻塞模式
                    clientChannel.configureBlocking(false);

                    //再在服务器的选择器上，注册第二个通道，并标识该通道所感兴趣的事情是接收客户端发来的消息
                    clientChannel.register(selector,SelectionKey.OP_READ);

                    //用key 四位随机数的形式模拟客户端的key
                    String key = "key"+(int)(Math.random()*9000+1000);

                    //将该建立完毕连接的通道保存到clientsMap中
                    clientsMap.put(key, clientChannel);

                    //读就绪已经可以读取客户端发来的消息了
                }else if(selectionKey.isReadable()){
                    clientChannel = (SocketChannel) selectionKey.channel();

                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);

                    int result = -1;

                    //如果终止客户端，则read()会抛出IOException,可以依次判断是否有客户端退出
                    try {
                        result = clientChannel.read(readBuffer);
                    }catch (IOException e){
                        //获取退出连接对应的key
                        String clientKey = getClientKey(clientChannel);

                        System.out.println("客户端："+clientKey+"退出聊天室");

                        clientsMap.remove(clientKey);
                        clientChannel.close();
                        selectionKey.cancel();
                        continue;
                    }

                    if(result > 0){
                        readBuffer.flip(); //读-->写
                        Charset charset = Charset.forName("utf-8");
                        String receive = String.valueOf(charset.decode(readBuffer).array());
                        //将收到消息打印到控制台
                        System.out.println(clientChannel+":"+receive);

                        //处理客户端第一次发来的连接测试信息
                        if("connectting".equals(receive)){
                            receive = "新客户端加入聊天";
                        }
                        //将读取到的客户端消息保存在attachment中，用于后续向所有客户端转发次消息
                        selectionKey.attach(receive);
                        //将通道所感兴趣的事件标识为：向客户端发送消息（写就绪）
                        selectionKey.interestOps(SelectionKey.OP_WRITE);
                    }
                }else if(selectionKey.isWritable()){
                    clientChannel = (SocketChannel) selectionKey.channel();

                    //获取发送消息从client取出对应的key
                    String sendKey = getClientKey(clientChannel);



                    //将接收到的消息，拼接成"发送消息的客户端Key：消息" 的形式。再广播给所有client
                    Set<Map.Entry<String, SocketChannel>> entries = clientsMap.entrySet();

                    for(Map.Entry<String, SocketChannel> entry : entries){
                        SocketChannel eachChannel =  entry.getValue();

                        ByteBuffer broadcastMsg = ByteBuffer.allocate(1024);

                        broadcastMsg.put((sendKey+":"+selectionKey.attachment()).getBytes());

                        broadcastMsg.flip();

                        eachChannel.write(broadcastMsg);

                    }
                    //将感兴趣的事件设置为读就绪
                    selectionKey.interestOps(SelectionKey.OP_READ);
                }

            }
            selectionKeys.clear();
        }
    }

    /**
     * 通过对应的通道拿到对应的退出连接的客户端Key
     * @param clientChannel
     * @return
     */
    private static String getClientKey(SocketChannel clientChannel) {
        String sendKey = null;

        Set<String> strings = clientsMap.keySet();
        Set<Map.Entry<String, SocketChannel>> entries = clientsMap.entrySet();
        for(Map.Entry<String, SocketChannel> entry : entries){
            if(entry.getValue()==clientChannel){
                sendKey = entry.getKey();
                break;
            }
        }
        return sendKey;
    }
}
