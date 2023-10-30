package com.etiya.campus.aj.fp;

import java.util.function.Function;

public class CompositionExample {

    public static void main(String[] args){
        Function<Integer,Integer> kare = x -> x*x;
        Function<Integer,Integer> kTopla = x -> x+x;
        Function<Integer,Integer> kareTopla = kTopla.compose(kare);
        System.out.println(kareTopla.apply(3));
        Function<Integer,Integer> toplaKare = kTopla.andThen(kare);
        System.out.println(toplaKare.apply(3));
    }
}
