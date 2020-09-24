package com.mystudy.lx;

import java.util.concurrent.DelayQueue;

/**
 * @author dalaoban
 * @create 2020-06-21-16:18
 */
public class Natatorium implements Runnable {

    private DelayQueue<Swimmer> delayQueue = new DelayQueue<>();

    //表示游泳馆是否开门
    private volatile boolean isOpen = true;

    public void addSwimmer(String name,int playTime){
        //规定的游泳时间
        long endTime = System.currentTimeMillis()+playTime*1000*60;
        Swimmer swimmer = new Swimmer(name,endTime);

        System.out.println(swimmer.getName()+"进入游泳馆，可供游泳时间："+playTime+"分");

        this.delayQueue.add(swimmer);  //没有剩余空间是会报异常的

    }


    @Override
    public void run() {
        try {
            while (isOpen){
                Swimmer swimmer = this.delayQueue.take();

                System.out.println(swimmer.getName()+"游泳时间结束");

                //全部取出时停止该线程
                if(delayQueue.size()==0){
                    isOpen=false;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

