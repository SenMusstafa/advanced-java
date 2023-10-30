package com.etiya.campus.aj.io;

import com.etiya.campus.aj.common.Product;

import java.util.Scanner;

public class ConsoleReadExample {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ürün Adını Giriniz");
        String productName = scanner.next();
        Product product = new Product(productName);
        System.out.println(product.getName() +" ürünü oluşturuldu");
    }
}
