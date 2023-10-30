package com.etiya.campus.aj.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SafeVarargs {
    public static void main(String[] args){
        //java9da List<String> colors = List.of("Red","Blue","Green");
        //List<String> colors1 = Arrays.asList("Red","Blue","Green");
        List<String> colors = new ArrayList<String>();
        colors.add("Red");
        colors.add("Blue");
        colors.add("Green");
        printList(colors);
        String[] types = typesTest("test");
    }
    @java.lang.SafeVarargs
    private static <T> void printList(T... items){
        for(T item:items){
            System.out.println(item);
        }
    }

    private static <T> T[] getTypes(T... types){
        return types;
    }

    private static <T> T[] typesTest(T type){
        T[] result = getTypes(type, type, type);
        return result;
    }
}
