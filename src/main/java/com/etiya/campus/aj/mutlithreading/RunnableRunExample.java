package com.etiya.campus.aj.mutlithreading;

public class RunnableRunExample {
    public static void main(String[] args) throws InterruptedException{
        System.out.println("Current Thread Name : "+Thread.currentThread().getName());

        Thread thread1 = new Thread(new RunnableExample());
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread Name : "+Thread.currentThread().getName());
                System.out.println("Thread2 RUN");
            }
        });
        thread2.start();

        Thread thread3 = new Thread(()->{
            System.out.println("Thread Name : "+Thread.currentThread().getName());
            System.out.println("Thread3 RUN");
            int x = 0;
            int y = 2;
            int z = y/x;
        });
        thread3.setName("controller");
        thread3.start();
        thread3.setUncaughtExceptionHandler((Thread t,Throwable exception)->{
            System.out.println("Exception : "+exception.getMessage());
        });
        thread3.join();
        System.out.println("Last Current Thread Name : "+Thread.currentThread().getName());
    }
}
