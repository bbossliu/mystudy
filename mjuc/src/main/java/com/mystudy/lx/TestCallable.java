package com.mystudy.lx;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author dalaoban
 * @create 2020-06-21-17:19
 */
public class TestCallable  {

    public static void main(String[] args) {
        MyCallable callable = new MyCallable();
        FutureTask<Integer> futureTask = new FutureTask<>(callable);

        new Thread(futureTask).start();

        try {
            //该方法会一直等待call方法执行完毕
            //即已闭锁的形式获取线程的返回值
            Integer sum = futureTask.get();

            System.out.println("1~100的和为："+sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

}

class MyCallable implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for(int i=1;i<=100;i++){
            sum+=i;
        }
        return sum;
    }
}
