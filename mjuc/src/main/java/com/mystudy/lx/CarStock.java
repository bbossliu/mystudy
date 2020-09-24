package com.mystudy.lx;

import java.util.concurrent.TimeUnit;

/**
 * @author dalaoban
 * @create 2020-06-27-15:30
 */
public class CarStock {

    int carNum ;

    public CarStock(int carNum) {
        this.carNum = carNum;
    }

    //生产者生产汽车
    public synchronized void productCar() {
      try {
          if(carNum<20){
              carNum++;
              System.out.println("生产者生产："+carNum);
              notifyAll();
              TimeUnit.SECONDS.sleep(1);
          }else {

              wait();
          }
      }catch (Exception e){
          e.printStackTrace();
      }
    }

    //消费者消费
    public synchronized void ConsumerCar() {
        try {
            if(carNum>0){
                System.out.println("消费者消费："+carNum);
                carNum--;
                notifyAll();
                TimeUnit.SECONDS.sleep(1);
            }else {
                wait();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

class ProductCar implements Runnable{

    CarStock carStock;

    public ProductCar(CarStock carStock) {
        this.carStock = carStock;
    }

    @Override
    public void run() {
        while (true)
        carStock.productCar();
    }
}

class ConsumerCar implements Runnable{

    CarStock carStock;

    public ConsumerCar(CarStock carStock) {
        this.carStock = carStock;
    }

    @Override
    public void run() {
        while (true)
        carStock.ConsumerCar();
    }
}

class TestConsumerAndProduct{
    public static void main(String[] args) {
        CarStock carStock = new CarStock(0);
        Thread t1 = new Thread(new ProductCar(carStock));
        Thread t2 = new Thread(new ConsumerCar(carStock));
        t1.start();
        t2.start();
    }
}

