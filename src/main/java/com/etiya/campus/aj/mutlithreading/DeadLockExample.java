package com.etiya.campus.aj.mutlithreading;

import com.etiya.campus.aj.domain.order.CustomerOrder;

public class DeadLockExample {
    private static int x;
    private static int y;
    private static final Object obj1 = new Object();
    private static final Object obj2 = new Object();
    CustomerOrder order = new CustomerOrder();

    public static void main(String[] args) throws InterruptedException {
        System.out.println("----------------------------------");
        x = 6;
        y = 4;
        Thread thread1 = new Thread(()->{
            System.out.println("Thread1 RUN");
            synchronized (obj1){
                System.out.println("Thread1 x+y : " + (x+y));
                synchronized (obj2){
                    System.out.println("Thread1 x-y : " + (x-y));
                }
            }
        });

        Thread thread2 = new Thread(()->{
            System.out.println("Thread2 RUN");
            synchronized (obj2){
                System.out.println("Thread2 x+y : " + (x+y));
                synchronized (obj1){
                    System.out.println("Thread2 x-y : " + (x-y));
                }
            }
        });

        thread1.start();
        thread2.start();

    }
}
