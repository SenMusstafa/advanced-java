package com.etiya.campus.aj.mutlithreading;

public class ThreadExample extends Thread{
    @Override
    public void run() {
        setName("ThreadExample");
        System.out.println("Thread Name : "+Thread.currentThread().getName());
        System.out.println("ThreadExample RUN");
    }
}
