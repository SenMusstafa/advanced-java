package com.etiya.campus.aj.mutlithreading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/*
uncompleted statede başlar
get() çağırıldığında bloklar
bir süre sonra publication event oluşur
bu değeri setler ve get ile bloklanan threadlere iletir
değer artık immutable
 */
public class CompletableFutureExample {
    public static void main(String[] args){
        CompletableFuture<Long> cp  = CompletableFuture.supplyAsync(()->{
           System.out.println("Start Current Thread : "+ Thread.currentThread().getName());
           return YapBisey.bekleBiraz(1);
        });
        CompletableFuture<Long> f1 =  cp.thenApply(n -> {
            System.out.println("Apply Current Thread : "+ Thread.currentThread().getName());
            return n*3;
        });
        CompletableFuture<Long> f2 =  cp.thenApplyAsync(n -> {
            System.out.println("Async Current Thread : "+ Thread.currentThread().getName());
            return n*4;
        });
        //buraya bir metod ekle n tane async
        //çoklayınca farklı threade attığı da görünüyor
        //thencompose farkı ne

        try {
            System.out.println("F1 : "+f1.get());
            System.out.println("F2 : "+f2.get());
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }

    }

    static class YapBisey{
        public static long bekleBiraz(int n){
            try {
                Thread.sleep(n*1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            return 1L;
        }
    }
}
