package com.mystudy.lx;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dalaoban
 * @create 2020-06-26-20:02
 */
public class TestCyclePrint {

    ReentrantLock lock = new ReentrantLock();

    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    private int num = 1;

    public static void main(String[] args) {
        TestCyclePrint test = new TestCyclePrint();


        ExecutorService executorService = Executors.newFixedThreadPool(3);

        while (true){
            executorService.submit(()->{
                test.print01();
            });

            executorService.submit(()->{
                test.print02();
            });

            executorService.submit(()->{
                test.print03();
            });
        }

//        executorService.shutdown();


    }


    private void print01() {
        lock.lock();
        try {
            if (num != 1) {

                condition1.await();

            }

            System.out.println(num);
            num = 2;

            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void print02() {
        lock.lock();
        try {
            if (num != 2) {

                condition2.await();

            }

            System.out.println(num);
            num = 3;

            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void print03() {
        lock.lock();
        try {
            if (num != 3) {

                condition3.await();

            }

            System.out.println(num);
            num = 1;

            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class TestCyclePrint02{

    public static void main(String[] args) {
        Semaphore semaphore1 = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(0);
        Semaphore semaphore3 = new Semaphore(0);

        TestCyclePrint02 test02 = new TestCyclePrint02();

      while (true){
          new Thread(()->{
              test02.print(semaphore1,semaphore2,"1");
          }).start();

          new Thread(()->{
              test02.print(semaphore2,semaphore3,"2");
          }).start();

          new Thread(()->{
              test02.print(semaphore3,semaphore1,"3");
          }).start();
      }

    }

    /**
     *
     * @param current 当前信号量
     * @param next 下个将要打印该值的信号量
     * @param vaule 当前值
     */
    private void print(Semaphore current, Semaphore next ,String vaule){
        try {
            //获得许可证
            current.acquire();
            System.out.println(vaule);
            TimeUnit.SECONDS.sleep(1);
            next.release();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

class TestCyclePrint03{
    public static void main(String[] args) {
        t_1 t_1 = new t_1(1,"ss");
        t_2 t_2 = new t_2(2,"ss");
        t_3 t_3 = new t_3(3,"ss");
        Thread t1 = new Thread(t_1);
        Thread t2 = new Thread(t_2);
        Thread t3 = new Thread(t_3);


        while (true){
            t_1.test1(t2);
            t_2.test2(t3);
            t_3.test3(t1);
        }


    }
}

class t_1 implements Runnable{

    Thread thread;
    int num;
    String sy;

    public t_1(int num, String sy) {
        this.num = num;
        this.sy = sy;
    }

    public void test1(Thread thread) {
        this.thread = thread;
    }


    @Override
    public void run() {
       synchronized (sy){
           try {
               if(num!=1){
                   this.thread.wait();
               }
               System.out.println(num);
               num = 2;
               thread.notify();
           }catch (Exception e){
               e.printStackTrace();
           }
       }
    }
}

class t_2 implements Runnable{

    Thread thread;
    int num;
    String sy;

    public t_2(int num, String sy) {
        this.num = num;
        this.sy = sy;
    }

    public void test2(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        synchronized (sy){
            try {
                if(num!=2){
                    this.thread.wait();
                }
                System.out.println(num);
                num = 3;
                thread.notify();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}


class t_3 implements Runnable{

    Thread thread;
    int num;
    String sy;

    public t_3(int num, String sy) {
        this.num = num;
        this.sy = sy;
    }

    public void test3(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void run() {
        synchronized (sy){
            try {
                if(num!=3){
                    this.thread.wait();
                }
                System.out.println(num);
                num = 1;
                thread.notify();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

class Tt1{
    public static void main(String[] args) {
        PrintCls printCls = new PrintCls(1);

        new Thread(() -> {
            while (true){
                printCls.print01();
            }
        }).start();

        new Thread(() -> {
            while (true){
                printCls.print02();
            }
        }).start();

        new Thread(() -> {
            while (true){
                printCls.print03();
            }
        }).start();


    }
}

class PrintCls{

    int value;

    public PrintCls(int value) {
        this.value = value;
    }

    /**
     *
     */
    public synchronized void print01(){
            try {
                while (true){
                    if(value!=1){
                        this.wait();
                    }else {
                        break;
                    }
                }
                System.out.println(value);
                value=2;
                TimeUnit.SECONDS.sleep(1);
                notifyAll();
            }catch (Exception e){
                e.printStackTrace();
            }
    }

    /**
     *
     */
    public synchronized void print02(){
            try {
                while (true){
                    if(value!=2){
                        this.wait();
                    }else {
                       break;
                    }
                }
                System.out.println(value);
                value=3;
                TimeUnit.SECONDS.sleep(1);
                notifyAll();
            }catch (Exception e){
                e.printStackTrace();
            }
    }


    /**
     *
     */
    public synchronized void print03(){
            try {
                while (true){
                    if(value!=3){
                        this.wait();
                    }else {
                        break;
                    }
                }
                System.out.println(value);
                value=1;
                TimeUnit.SECONDS.sleep(1);
                notifyAll();
            }catch (Exception e){
                e.printStackTrace();
            }
    }
}
