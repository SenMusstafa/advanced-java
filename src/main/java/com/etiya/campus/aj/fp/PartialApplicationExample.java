package com.etiya.campus.aj.fp;

import java.util.function.BiFunction;
import java.util.function.Function;

public class PartialApplicationExample {
    public static void main(String[] args){
        FTriParamFunction<Double, Double, Double, Double> topla = (x, y, z) -> x+y+z;

        Function<Double, BiFunction<Double, Double, Double>> kismiTopla =
                (x) -> (y,z) -> topla.apply(x,y,z);

        BiFunction<Double, Double, Double> topla1 = kismiTopla.apply(new Double(7));

        System.out.println(topla1.apply(new Double(10), new Double(3)));

    }
}
