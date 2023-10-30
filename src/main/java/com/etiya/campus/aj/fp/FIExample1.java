package com.etiya.campus.aj.fp;

import java.util.function.Function;

public class FIExample1 {
    protected static class Calculate {
    public static Integer add20(Integer n){
            return n + 20;
        }
    }
    public static void main(String[] args){
        Function<Integer,Integer> a20 = Calculate::add20;
        Integer b = a20.apply(50);
    }
}
