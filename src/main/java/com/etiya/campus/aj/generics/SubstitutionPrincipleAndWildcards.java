package com.etiya.campus.aj.generics;

import com.etiya.campus.aj.common.*;
import com.etiya.campus.aj.domain.validation.IValidator;


import java.util.ArrayList;
import java.util.List;

public class SubstitutionPrincipleAndWildcards {
    public static void main(String[] args){
        Product product = new Product("Süpürge");
        Phone phone = new Phone("Samsung Galaxy S20");
        printProduct(product);
        printProduct(phone);

        List<Product> productList = new ArrayList<>();
        /*
        Phone sınıfı Product sınıfının alt türü (subtype) olduğu için
        ikisini de parametre olarak Product alan printProducts metoduna yollayabiliriz
         */
        productList.add(phone);
        productList.add(product);
        printProducts(productList);

        /*
        List<Phone> List<Product> un alt türü (subtype) olmaz
         */
        List<Product> productList1 = new ArrayList<>();
        List<Phone> phoneList1 = new ArrayList<>();

        Phone phone1 = new Phone("11");
        Product product1 = new Product("22");
        product1 = phone1;
        /*
        Phone sınıfı Product sınıfının alt türü (subtype)
        Fakat aşağıdaki productList1 = phoneList1; satırı hata alır. Almasaydı ne olurdu?
        productList1'e Computer tipinde bir nesne de ekleyebiliriz çünkü o da Product sınıfının alt türü.
         */
        //productList1 = phoneList1;
        //productList1.add(new Computer("Asus"));

        List<Phone> phoneList2 = new ArrayList<>();
        List<Computer> computerList2 = new ArrayList<>();
        /*
        Extends olarak tanımladığımız zaman aşağıdaki şekilde
        alt türü ile tanımlı bir listeyi atayabiliyoruz
         */
        List<? extends Product> productList2 = phoneList2;
        /*
        Extends kullanımını sadece listeden okuma yapacağımız durumlar için tercih etmeliyiz
         */
        //productList2.add(new Computer("Acer"));
        /*
        Extend edilen sınıfın özelliklerini ya da metodlarını kullanabiliriz
         */
        printNames(productList2);

        /*
        Aşağıdaki gibi tanımlama yapabiliriz
         */
        List<? extends Product> phoneList3 = new ArrayList<Phone>();
        List<? extends Product> computerList3 = new ArrayList<Computer>();

        /*
        Super kullanımını sadece ekleme yapacağımız durumlar için tercih etmeliyiz
         */
        List<? super Computer> productList3 = new ArrayList<Product>();
        productList3.add(new LaptopComputer("s"));
        addLaptop(productList3,"Lenovo");

        /*
        Eğer hem ekleme hem okuma yapmak gerekiyorsa extend veya super ile wildcard kullanımı yapmamak gerekir
         */

        /*
        Yeni instance oluşltururken aşağıdaki şekilde wildcard kullanamayız
         */
        //List<? extends Product> productList3 = new ArrayList<>();

        List<List<?>> listList = new ArrayList<List<?>>();
        List<?> list1 = new ArrayList<Object>();
        //List<?> list2 = new ArrayList<?>();
    }

    private static void printNames(List<? extends Product> list){
        list.forEach(product -> System.out.println(product.getName()));
    }

    private static void addLaptop(List<? super Computer> list, String name){
        list.add(new LaptopComputer(name));
        list.add(new DesktopComputer(name));
    }

    private static void printProduct(Product product){
        System.out.println(product.getName());
    }

    private static void printProducts(List<Product> products){
        for(Product product:products){
            System.out.println(product.getName());
        }
    }
}
