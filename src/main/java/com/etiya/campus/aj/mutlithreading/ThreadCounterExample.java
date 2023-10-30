package com.etiya.campus.aj.mutlithreading;

import java.util.ArrayList;
import java.util.List;

public class ThreadCounterExample {
    private static int counter=0;
    private static final Object obj = new Object();

    public static void main(String[] args){
        ThreadGroup group = new ThreadGroup("CounterGroup");
        List<Thread> counterThreads = new ArrayList<>();
        for (int i = 1; i<=100; i++) {
            Thread t = new Thread(group, new CounterThread());
            t.start();
            counterThreads.add(t);
        }
        counterThreads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("Total = " + counter);
    }
    static class CounterThread implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread currentThread = Thread.currentThread();
                System.out.println("Interrupted : " + currentThread.getName() );
            }
            //counter++;
            /*
            sync bloğuna girdiğinde monitörü alır çıkınca bırakır
             */
            synchronized (obj){
                counter++;
            }
        }
    }
}
