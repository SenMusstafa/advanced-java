package com.etiya.campus.aj.mutlithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {
    private static int counter=0;
    private static ReentrantLock lock = new ReentrantLock();

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
                lock.lock();
                Thread.sleep(50);
                counter++;
            } catch (InterruptedException e) {
                Thread currentThread = Thread.currentThread();
                System.out.println("Interrupted : " + currentThread.getName() );
            }
            finally {
                lock.unlock();
            }
        }
    }
}
