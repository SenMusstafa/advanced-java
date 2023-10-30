package com.etiya.campus.aj.datastructures;

import com.etiya.campus.aj.common.InternContainer;
import com.etiya.campus.aj.domain.product.DefaultProduct;
import com.etiya.campus.aj.util.DataGenerator;
import com.etiya.campus.aj.common.Product;
import com.etiya.campus.aj.util.PrintUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListExample {
    Logger log = LogManager.getLogger(ListExample.class.getName());
    private static PrintUtil pr = new PrintUtil();
    public static void main(String[] args){
       //arrayListExample();
        //failFastExample();
        //linkedListExample();
        stackExample();
        //vectorExample();

        //copyOnWriteArrayListExample();
        //kullanim();

    }

    private static void kullanim(){
        List<DefaultProduct> activatedProducts = new ArrayList<>();
        List<DefaultProduct> deactivatedProducts = new ArrayList<>();
        DefaultProduct product1 = new DefaultProduct("111111","Ev Avantaj 100");
        DefaultProduct product2 = new DefaultProduct("222222","Ev Avantaj 200");
        activatedProducts.add(product1);activatedProducts.add(product2);
        activatedProducts.forEach(product -> System.out.println(product.getProductNumber()));
        System.out.println("----------");
        product2.setProductNumber("334434343");
        activatedProducts.forEach(product -> System.out.println(product.getProductNumber()));



    }

    private static void arrayListExample(){
        DataGenerator dg = new DataGenerator();
        List<Product> productList = dg.productList1();
        pr.printProducts(productList);
        /*
        Aynı kayıtları terkar eklediyoruz
         */
        productList.addAll(dg.productList1());
        pr.printProducts(productList);

        Product product = dg.generateProduct(1);
        /*
        Contains metodu nesnenin equals metodunu kullanarak karşılaştırma yapar
         */
        Product newProduct = new Product("Product10");
        newProduct.setProductNum("N10");
        System.out.println("Contains New Product= "+ productList.contains(newProduct));
        System.out.println("Contains = "+productList.contains(product));
        product.setProductNum("121212");
        System.out.println("Contains = "+productList.contains(product));
    }


    private static void copyOnWriteArrayListExample(){
        /*
        ArrayList sınıfının (synchronization olmadan) thread safe bir versiyonudur.
        Değişitiren bütün işlemlerde (add, set gibi) yeni bir kopyası oluşur.
        threadlerde örnek
         */
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>(new String[]{"Samsung","Huawei","Apple"});
        Iterator<String> iterator = list.iterator();
        /*
        iterator oluşturduğumuzda o anda içinde olan verinin değiştirlemez halini alırız.
        Bu durumda listede Xiaomi olmayacaktır.
         */
        list.add("Xiaomi");
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        /*
        iterate ederken remove çağırılamaz UnsupportedOperationException fırlatır.
         */
    }

    private static void linkedListExample(){
        /*
        Array : iterasyon ve belli bir indexteki elemana erişmek daha hızlı, belirli bir indexe ekleme çıkarma daha yavaş
        ArrayList elemanları bir dinamik arrayde turar. Bir elemanı çıkardığımızda diğer elemanlar kaydırılır.
        Elemanların memory yerleşimi bitişiktir.
        Linkedlist hem List hem Deque interface lerini implemente eder hem liste hem queue gibi kullanılabilir.
        LinkedList elemanlara poziyon ile erişim yavaş , belli pozisyona ekleme çıkarma hızlı
         */
        LinkedList<Integer> list1 = new LinkedList<>();
        for(int i = 1;i<101;i++) list1.add(new Integer(i));
        System.out.println("First " +list1.getFirst());
        System.out.println("Last = " +list1.getLast());
        for(int i = 0; i<10;i++) System.out.println(list1.peek());
        for(int i = 0; i<10;i++) System.out.println(list1.pop());
        //System.out.println("--------");
        //list1.forEach(System.out::println);
        System.out.println("80 var mı = " +list1.contains(80));
        System.out.println("4 var mı = " +list1.contains(4));
        //List list = Collections.synchronizedList(new LinkedList(...));
    }

    private static void failFastExample(){
        try {
            LinkedList<Integer> list1 = new LinkedList<>();
            for(int i = 1;i<101;i++) list1.add(new Integer(i));
            Iterator<Integer> it = list1.iterator();
            while (it.hasNext()){
                Integer i = it.next();
                System.out.println(i);
                list1.add(698);
            }
        }catch (ConcurrentModificationException  cme){
            System.out.println("Liste değiştirilemez");
        }
    }

    private static void stackExample(){
        /*
        LIFO prensibi ile çalışır
        Vector sınıfının alt türü olduğu için synchronized
         */
        Stack<Integer> stack = new Stack<>();
        for(int i = 1;i<101;i++) stack.push(new Integer(i));
        for(int i = 0; i<10;i++) System.out.println(stack.peek());
        for(int i = 0; i<10;i++) System.out.println(stack.pop());
        System.out.println(stack.peek());
        System.out.println("80 var mı = " +stack.search(80));
        System.out.println("94 var mı = " +stack.search(94));
        printStackTrace();
    }

    private static void vectorExample(){
        /*
        thread safe olmasına ihtiyaç varsa kullanmak daha iyi olur gerek yoksa ArrayList daha iyi performans verir.
        Synchronised
         */
        Vector<Integer> vec = new Vector<>(100,10);
        for(int i = 1;i<101;i++) vec.add(new Integer(i));
        vec.forEach(System.out::println);
    }

    private static void printStackTrace(){
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        for(int i = 0;i<=stackTraceElements.length-1;i++){
            System.out.println(i+" - "+stackTraceElements[i].getClassName()+"."+stackTraceElements[i].getMethodName());
        }
    }

}
