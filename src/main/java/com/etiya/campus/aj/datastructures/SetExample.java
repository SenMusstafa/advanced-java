package com.etiya.campus.aj.datastructures;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetExample {
    enum Types {PRODUCT,ORDER,ACCOUNT,CUSTOMER};

    public static void main(String[] args){
        //treeSetExample();
        //concurrentSkipListSetExample();
        //copyOnWriteArraySetExample();
        //enumSetExample();

    }
    private static void treeSetExample(){
        /*
        Ekleme sırasını korumaz
        Artan şekilde sıralar
        Thread-safe değildir
         */
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 1;i<10;i++) set.add(new Integer(i));
        System.out.println(set);
        Set<Integer> subSet = set.subSet(2,4);
        System.out.println("---------------------");
        System.out.println("Subset");
        System.out.println(subSet);
        Set<Integer> headSet = set.headSet(3);
        System.out.println("---------------------");
        System.out.println("Headset");
        System.out.println(headSet);
        Set<Integer> tailSet = set.tailSet(7);
        System.out.println("---------------------");
        System.out.println("Tailset");
        System.out.println(tailSet);
    }
    private static void concurrentSkipListSetExample(){
        /*
        TreeSet dengi Thread-safe
        Sıralı ve birden fazla thread tarafından erişilebilir bir set ihtiyacını karşılar
         */
        ConcurrentSkipListSet<Integer> set = new ConcurrentSkipListSet<>();
        for(int i = 1;i<101;i++) set.add(new Integer(i));
        System.out.println("Last : " +set.last());
        System.out.println("First : " +set.first());
        System.out.println("Poll Last : " +set.pollLast());
        System.out.println("Poll First : " +set.pollFirst());
        System.out.println("Last : " +set.last());
        System.out.println("First : " +set.first());
    }

    private static void copyOnWriteArraySetExample(){
        /*
        Işlemleri için kendi içinde CopyOnWriteArrayList kullanır aynı temelk özelliklere sahiptir
        Thread safe
        Değişitiren bütün işlemlerde (add, set gibi) yeni bir kopyası oluşur.
         */
        CopyOnWriteArraySet<Integer> set = new CopyOnWriteArraySet<>();
        for(int i = 1;i<101;i++) set.add(new Integer(i));
    }

    private static void enumSetExample(){
        /*
        Enum tipi ile kullanmak içindir
        Bütün elemanları tanımlanan enumdan olmalıdır
         */

        EnumSet<Types> set =EnumSet.of(Types.PRODUCT,Types.ORDER);
        EnumSet<Types> set1 =EnumSet.allOf(Types.class);
        EnumSet<Types> set3 =EnumSet.complementOf(set);
        set3.forEach(System.out::println);
        //niye kullanabilirim validationset
    }

    private static void hashSetExample(){
        /*
        HashMap kullanır
        Ekleme sırasını korumaz
        Thread-safe değildir.
         */

        HashSet<Integer> set = new HashSet<>();
        for(int i = 1;i<101;i++) set.add(new Integer(i));
    }

    private static void linkedHashSetExample(){
        /*
        HashSet'in sıralı versiyonu
        Linked list kullanır
        Ekleme sırası ile işleyebiliriz
         */

        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        for(int i = 1;i<101;i++) set.add(new Integer(i));
    }
}
