package com.etiya.campus.aj.mutlithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Phaser;

/*
Threadler kendini register edebilir.
bu da barrier, parites sayısı dinamik değişebiliyor
 */
public class PhaserExample {
    private static Phaser phaser = new Phaser();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("----------------------------------");
        Thread bsnInterCreator = new Thread(new BsnInterCreator());
        Thread productCreator = new Thread(new ProductCreator());
        Thread orderCharCreator = new Thread(new OrderCharCreator());
        bsnInterCreator.start();
        productCreator.start();
        orderCharCreator.start();
        bsnInterCreator.join();
        productCreator.join();
        orderCharCreator.join();
        phaser.arriveAndAwaitAdvance();
        System.out.println("----------------------------------");
    }
    static class BsnInterCreator implements Runnable{
        public BsnInterCreator(){
            phaser.register();
        }

        @Override
        public void run() {
            System.out.println("BsnInterCreator Start");
            try {
                Thread.sleep(1000);
                System.out.println("BsnInterCreator Validated");
                phaser.arriveAndAwaitAdvance();
                Thread.sleep(1000);
                System.out.println("BsnInterCreator Done");
                phaser.arriveAndAwaitAdvance();
                System.out.println("BsnInterCreator Await Done");
                phaser.arriveAndDeregister();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }

    static class ProductCreator implements Runnable{
        public ProductCreator(){
            phaser.register();
        }

        @Override
        public void run() {
            System.out.println("ProductCreator Start");
            try {
                Thread.sleep(3000);
                System.out.println("ProductCreator Validated");
                phaser.arriveAndAwaitAdvance();

                Thread.sleep(3000);
                System.out.println("ProductCreator Done");
                phaser.arriveAndAwaitAdvance();
                System.out.println("ProductCreator Await Done");
                phaser.arriveAndDeregister();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    static class OrderCharCreator implements Runnable{
        public OrderCharCreator(){
            phaser.register();
        }

        @Override
        public void run() {
            System.out.println("OrderCharCreator Start");
            try {
                Thread.sleep(2000);
                System.out.println("OrderCharCreator Validated");
                phaser.arriveAndAwaitAdvance();

                Thread.sleep(2000);
                System.out.println("OrderCharCreator Done");
                phaser.arriveAndAwaitAdvance();
                System.out.println("OrderCharCreator Await Done");
                phaser.arriveAndDeregister();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
