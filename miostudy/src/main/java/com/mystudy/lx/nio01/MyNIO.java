package com.mystudy.lx.nio01;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Paths;

/**
 * @author dalaoban
 * @create 2020-06-14-17:45
 */
public class MyNIO {
    public static void main(String[] args) {
        try {

            ByteBuffer buffer = ByteBuffer.allocate(20);

            buffer.putDouble(1.34159D);

            buffer.putChar('刘');
            //将写变为读模式
            buffer.flip();

            System.out.println(buffer.getDouble());
            System.out.println(buffer.getChar());

//            FileChannel open = FileChannel.open(Paths.get(""));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
