package com.etiya.campus.aj.fp;

import java.util.function.Function;

public class LambdaExample {
    public static void main(String[] args){
        //lambda expression: (price) -> price * 10;
        Function<Integer,Integer> zamFunction = (price) -> price * 10;
        zamFunction.apply(3);
    }
}
