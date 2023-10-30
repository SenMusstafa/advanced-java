package com.etiya.campus.aj.mutlithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();

    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args){
        Thread writer = new Thread(new Writer());
        Thread reader1 = new Thread(new Reader());
        Thread reader2 = new Thread(new Reader());

        writer.start();
        reader1.start();
        reader2.start();
    }

    static class Writer implements Runnable{
        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(10000);
                    System.out.println("WriteLock Lock");
                    writeLock.lock();

                    int value = (int) (Math.random() * 10);
                    System.out.println("Writing: " + value);

                    Thread.sleep(3000);

                    list.add(value);
                    System.out.println("WriteLock Unlock");
                    writeLock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Reader implements Runnable{
        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(3000);

                    while (true) {
                        boolean acquired = readLock.tryLock();
                        if (acquired) {
                            break;
                        } else {
                            System.out.println("Waiting for read lock");
                        }
                    }

                    System.out.println("List: " + list);
                    readLock.unlock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
