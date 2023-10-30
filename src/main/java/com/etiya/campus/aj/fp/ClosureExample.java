package com.etiya.campus.aj.fp;

import com.etiya.campus.aj.common.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ClosureExample {
    public static void main(String[] args){
        Function<String,Function<String,String>> addPrefix = (str) -> {
            String prefix = "x_";
            //Fonksyonumuz bir fonksyon döndürüyor
            return (sstr) -> prefix+sstr;
        };

        Function<String,String> prefixAdder = addPrefix.apply("");
        System.out.println(prefixAdder.apply("chair"));
    }
}
