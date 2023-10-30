package com.etiya.campus.aj.mutlithreading;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapExample {
    //private static Map<String,String> map = new HashMap<>();
    private static Map<String,String> map = new ConcurrentHashMap<>();
    public static void main(String[] args) throws InterruptedException{
        Thread thread1 = new Thread(()->{
           for(int i = 0;i<100;i++) map.put("thread1_"+i,"T1");
        });
        Thread thread2 = new Thread(()->{
            for(int i = 0;i<100;i++) map.put("thread2_"+i,"T2");
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("Map size : "+map.size());

    }
}
