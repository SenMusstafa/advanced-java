package com.etiya.campus.aj.mutlithreading;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
/*
CyclicBarrier threadlerin birbirlerini beklemelerini sağlar
3 thread başladı barierımızın await metodunu 3 thread çağırabilir
hepsi çağırınca işlem devam eder
bir işi parçalara bölüp farklı threadlere atayıp en son birleştirmek için kullanılır

 */
public class CyclicBarrierExample {
    private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> System.out.println("Barrier OK"));

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
        System.out.println("----------------------------------");
    }

    static class BsnInterCreator implements Runnable{

        @Override
        public void run() {
            System.out.println("BsnInterCreator Start");
            try {
                Thread.sleep(1000);
                System.out.println("BsnInterCreator Done");
                cyclicBarrier.await();
                System.out.println("BsnInterCreator Await Done");
            }catch (BrokenBarrierException be){
                be.printStackTrace();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

        }
    }

    static class ProductCreator implements Runnable{

        @Override
        public void run() {
            System.out.println("ProductCreator Start");
            try {
                Thread.sleep(3000);
                System.out.println("ProductCreator Done");
                cyclicBarrier.await();
                System.out.println("ProductCreator Await Done");
            }catch (BrokenBarrierException be){
                be.printStackTrace();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    static class OrderCharCreator implements Runnable{

        @Override
        public void run() {
            System.out.println("OrderCharCreator Start");
            try {
                Thread.sleep(2000);
                System.out.println("OrderCharCreator Done");
                cyclicBarrier.await();
                System.out.println("OrderCharCreator Await Done");
            }catch (BrokenBarrierException be){
                be.printStackTrace();
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
