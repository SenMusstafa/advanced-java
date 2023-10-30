package com.etiya.campus.aj.mutlithreading;

import java.util.concurrent.Exchanger;

/*
İki thread arasında veri değişimi için kullanılır
 */
public class ExchangerExample {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("----------------------------------");
        Exchanger<String> exchanger = new Exchanger<>();

        Thread thread1 = new Thread(()->{
            try {
                System.out.println("Thread1 Start");
                String receivedValue = exchanger.exchange("Thread1 Gonderdi");
                System.out.println("Thread1 Recieved : "+receivedValue);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(()->{
            try {
                System.out.println("Thread2 Start");
                String receivedValue = exchanger.exchange("Thread2 Gonderdi");
                System.out.println("Thread2 Recieved : "+receivedValue);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();
        System.out.println("----------------------------------");
    }
}
