package com.etiya.campus.aj.datastructures;

import com.etiya.campus.aj.common.DelayObject;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.*;

public class QueueExample {
    public static void main(String[] args){
        //arrayBlockingQueueExample();
        //arrayDequeExample();
        //concurrentLinkedDequeExample();
        //concurrentLinkedQueueExample();
        //delayQueueExample();
        //linkedBlockingDequeExample();
        //linkedBlockingQueueExample();
        //linkedTransferQueueExample();
        //priorityQueueExample();
        priorityBlockingQueueExample();
        //synchronousQueueExample();
    }
    private static void arrayBlockingQueueExample(){
        /*
        FIFO
        Başta verilen kapasite sabittir.
         */

        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<>(100);
        for(int i = 1;i<101;i++) queue.add(new Integer(i));
        queue.add(9999);
    }
    private static void arrayDequeExample(){
        /*
        İki uçtan ekleme yapılabilir
        Boyut sınırlaması yoktur
        Çok sayıda ekleme çıkarma yapıldığında performanslı çalışır
        Daha az memory kaplar, sınırlı memory olduğu durumda kullanılabilir
        Thread safe değildir. Gerekli ise Collections.synchronizedDeque kullnanılabilir.
         */

        Deque<Integer> queue = new ArrayDeque<>();
        for(int i = 1;i<101;i++) queue.add(new Integer(i));
        queue.addFirst(0);
        queue.addLast(9999);
    }

    private static void concurrentLinkedDequeExample(){
        /*
        Eş zamanlı ekleme çıkarma ve erişim birden fazla thread üzerinden yapılabilir.
        addAll, removeAll, retainAll, containsAll, equals, ve toArray gibi operasyonların
        bütün elemanlara uygulanabileceği garanti edilemez.
        Kapasite limiti yoktur ama fazla memory kapladığında yeni bir tane oluşturmak gerekir.
         */

        Deque<Integer> queue = new ConcurrentLinkedDeque<>();
        for(int i = 1;i<101;i++) queue.add(new Integer(i));
        queue.addFirst(0);
        queue.addLast(9999);
        int first = queue.pollFirst();
        int last = queue.pollLast();
    }

    private static void concurrentLinkedQueueExample(){
        /*
        FIFO
        Birden fazla thread üzerinden erişilebilir.
        addAll, removeAll, retainAll, containsAll, equals, ve toArray gibi operasyonların
        bütün elemanlara uygulanabileceği garanti edilemez.
         */

        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
        for(int i = 1;i<101;i++) queue.add(new Integer(i));
    }

    private static void delayQueueExample(){
        /*
        Bir eleman delay süresi dolduğunda işlenebilir.
        Kuyruğun başı, delay süresi en önce dolmuş olan elemandır.

         */

        BlockingQueue<DelayObject> queue = new DelayQueue<>();
        queue.add(new DelayObject("1",1));
        queue.add(new DelayObject("2",2));
        queue.add(new DelayObject("3",3));
        queue.add(new DelayObject("4",4));
    }

    private static void linkedBlockingDequeExample(){
        /*
        Deque'nun thread safe implementasyonu
        Elemanları linked list içinde tutar
        Stack veya queue gibi kullanılabilir
        Çok sayıda ekleme çıkarma yapıldığında performanslı çalışır
        Çok sayıda thread lock olmadan kullanabilir
        Boş olduğu zaman remove dolu olduğu zaman ekleme işlemlerinde threadleri bloklar
         */

        LinkedBlockingDeque<Integer> queue = new LinkedBlockingDeque<Integer>(3);
        queue.add(1);
        queue.add(2);
        queue.add(3);
        try{
            queue.add(4);
        }catch (Exception e){
            System.out.println("Queue kapasitesi dolu.");
        }
    }

    private static void linkedBlockingQueueExample(){
        /*
        FIFO
        Kapasite belirlenerek sınırlandırılabilir
         */

        LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println(queue);
    }

    private static void linkedTransferQueueExample(){
        /*
        FIFO
        Producer-consumer
        Producer transfer() meotu ile mesaj gönderdiğinde consumer take() metodu ile
        mesajı alana kadar bloklanır
         */

        LinkedTransferQueue<Integer> queue = new LinkedTransferQueue<Integer>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        System.out.println(queue);
    }

    private static void priorityQueueExample(){
        /*
        FIFO
        Eklenen nesneler Comparable olmalı
         */

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        for(int i = 1;i<101;i++) queue.add(new Integer(i));
        for(int i = 0; i<10;i++) System.out.println(queue.peek());
        for(int i = 0; i<10;i++) System.out.println(queue.poll());
        System.out.println("80 var mı = " +queue.contains(80));
        System.out.println("4 var mı = " +queue.contains(4));
    }

    private static void priorityBlockingQueueExample(){
        /*
        FIFO
        Eklenen nesneler Comparable olmalı
         */

        PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<Integer>();
        for(int i = 1;i<101;i++) queue.add(new Integer(i));
        for(int i = 0; i<10;i++) System.out.println(queue.peek());
        for(int i = 0; i<10;i++) System.out.println(queue.poll());
        System.out.println("80 var mı = " +queue.contains(80));
        System.out.println("4 var mı = " +queue.contains(4));
        //queue.drainTo()
        //queue.stream().map()
    }

    private static void synchronousQueueExample(){
        /*
        Threadler arası veri değişimini thread-safe olarak yapmayı sağlar.
        take() ve put() metodları vardır ve threadi bloklarlar
         */

        SynchronousQueue<Integer> queue = new SynchronousQueue<Integer>();
        for(int i = 1;i<101;i++) queue.add(new Integer(i));
        for(int i = 0; i<10;i++) System.out.println(queue.peek());
        for(int i = 0; i<10;i++) System.out.println(queue.poll());
        System.out.println("80 var mı = " +queue.contains(80));
        System.out.println("4 var mı = " +queue.contains(4));
        //queue.drainTo()
        //queue.stream().map()
    }
}
