package com.etiya.campus.aj.generics;

import java.util.ArrayList;
import java.util.List;

public class GenericMethod {

    public static void main(String[] args){
        List<Integer> intList = new ArrayList<Integer>();
        intList.add(1);
        intList.add(2);
        List<String> stringList = new ArrayList<String>();
        stringList.add("Bir");
        stringList.add("İki");
        printList(intList);
        printList(stringList);
    }

    public static <T> void printList(List<T> list){
        list.stream().forEach(System.out::println);
    }
}
