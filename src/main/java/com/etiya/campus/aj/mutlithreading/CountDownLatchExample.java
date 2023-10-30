package com.etiya.campus.aj.mutlithreading;

import java.util.concurrent.CountDownLatch;

/*
CountDownLatch bir işi yapmak için threadlerin bitmesini bekler
 */
public class CountDownLatchExample {
    private static CountDownLatch countDownLatch = new CountDownLatch(3);
    public static void main(String[] args) throws InterruptedException {
        System.out.println("----------------------------------");
        Thread bsnInterCreator = new Thread(new BsnInterCreator());
        Thread productCreator = new Thread(new ProductCreator());
        Thread orderCharCreator = new Thread(new OrderCharCreator());
        bsnInterCreator.start();
        productCreator.start();
        orderCharCreator.start();
        countDownLatch.await();
        System.out.println("----------------------------------");
    }

    static class BsnInterCreator implements Runnable{

        @Override
        public void run() {
            System.out.println("BsnInterCreator Start");
            try {
                Thread.sleep(1000);
                System.out.println("BsnInterCreator Done");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }
    }

    static class ProductCreator implements Runnable{

        @Override
        public void run() {
            System.out.println("ProductCreator Start");
            try {
                Thread.sleep(3000);
                System.out.println("ProductCreator Done");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }
    }

    static class OrderCharCreator implements Runnable{

        @Override
        public void run() {
            System.out.println("OrderCharCreator Start");
            try {
                Thread.sleep(2000);
                System.out.println("OrderCharCreator Done");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            countDownLatch.countDown();
        }
    }
}
