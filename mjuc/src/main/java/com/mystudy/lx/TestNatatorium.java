package com.mystudy.lx;

/**
 * @author dalaoban
 * @create 2020-06-21-16:27
 */
public class TestNatatorium {

    public static void main(String[] args) {
       try {
           Natatorium natatorium = new Natatorium();
           new Thread(natatorium).start();

           natatorium.addSwimmer("zs",1);
           natatorium.addSwimmer("ls",2);
           natatorium.addSwimmer("wy",3);
       }catch (Exception e){
           e.printStackTrace();
       }
    }
}
