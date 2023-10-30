package com.etiya.campus.aj.fp;

import java.util.function.BiFunction;

public class BiFunctionExample {
    public static void main(){
        BiFunction<Integer, Integer, Integer> topla = (x,y) -> x+y;
        topla.apply(3,6);
    }
}
