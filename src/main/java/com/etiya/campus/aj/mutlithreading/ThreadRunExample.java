package com.etiya.campus.aj.mutlithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadRunExample {
    public static void main(String[] args)throws InterruptedException{
        try{
            ThreadExample thread1 = new ThreadExample();
            thread1.start();

            //thread1.setDaemon(true);



            ExecutorService executorService = Executors.newFixedThreadPool(2);
            Future<Integer> future = executorService.submit(new CallableExample());
            Integer result = future.get();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
