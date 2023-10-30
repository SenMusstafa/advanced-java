package com.etiya.campus.aj.mutlithreading;

public class ThreadGroupExample {
    public static void main(String[] args) throws InterruptedException{
        ThreadGroup group = new ThreadGroup("ExampleGroup");
        Thread thread1 = new Thread(group, new ForeverSleepingThread(), "Thread1");
        Thread thread2 = new Thread(group, new ForeverSleepingThread(), "Thread2");
        Thread thread3 = new Thread(group, new ForeverSleepingThread(), "Thread3");

        thread1.setPriority(Thread.MAX_PRIORITY);

        thread1.start();
        thread2.start();
        thread3.start();

        System.out.println("Sleep");
        Thread.sleep(3000);
        group.interrupt();
    }

    static class ForeverSleepingThread implements Runnable {
        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Thread currentThread = Thread.currentThread();
                    System.out.println("Interrupted : " + currentThread.getName() );
                }
            }
        }
    }
}
