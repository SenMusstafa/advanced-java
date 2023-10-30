package com.etiya.campus.aj.mutlithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableRunExample {
    public static void main(String[] args){
        ExecutorService exec = Executors.newFixedThreadPool(1);
        CallableExample example = new CallableExample();
        Future<Integer> future = exec.submit(example);
        try {
            while(!future.isDone()){
                System.out.println("RUNNING");
                Thread.sleep(500);
            }
            Integer result = future.get();
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            exec.shutdownNow();
        }
    }
}
