package com.etiya.campus.aj.util;

import com.etiya.campus.aj.common.Product;

import java.util.List;
import java.util.Map;

public class PrintUtil {
    public void printProducts(List<Product> list){
        if(list.isEmpty()) System.out.println("EMPTY LIST");
        list.forEach(System.out::println);
        System.out.println("##################################");
    }

    public void printMap(Map<String,String> map){

    }
}
