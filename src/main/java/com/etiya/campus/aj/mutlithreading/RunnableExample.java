package com.etiya.campus.aj.mutlithreading;

public class RunnableExample implements Runnable{
    @Override
    public void run(){
        System.out.println("Thread Name : "+Thread.currentThread().getName());
        System.out.println("RunnableExample RUN");
    }
}
