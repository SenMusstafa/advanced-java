package com.etiya.campus.aj.fp;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class FunctionalInterfacesExample {

    public static void main(String[] args){
        functionalInterfaceExample();
       //functionExample();
        //consumerExample();
        //supplierExample();
        //predicateExample();
        //effectivelyFinalExample();

    }

    private static void functionalInterfaceExample(){
        FProductCharValue value1 = new FProductCharValue() {
            @Override
            public String displayValue(String prodCharId) {
                return "NewValue for : "+prodCharId;
            }
        };

        System.out.println(value1.displayValue("11"));
        //Java 8 oncesi

        //Java 8 sonrası
        FProductCharValue value2 = (String prodCharId) -> "NewValue for : "+prodCharId;

        System.out.println(value2.displayValue("22"));
    }

    private static void functionExample(){
        Function<Integer,Integer> a20 = FIExample1.Calculate::add20;
        Function<Integer,Integer> a30 = num -> num + 30;
        Integer b = a20.apply(50);
        Integer c = a30.apply(50);
        System.out.println(b);
        System.out.println(c);
    }

    private static void consumerExample(){
        Consumer<String> addPrefixAndDisplay = name -> System.out.println("t_"+name);
        addPrefixAndDisplay.accept("order");
    }

    private static void supplierExample(){
        Supplier<Double> getRandom =  Math::random;
        System.out.println(getRandom.get());
    }
    private static void predicateExample(){
        Predicate<String> prefixCheck = s->s.startsWith("t_");
        List<String> list = new ArrayList<>();
        list.add("t_cust_ord");list.add("t_prod");list.add("cust_ord");
        List<String> list1 = list.stream().filter(prefixCheck).collect(Collectors.toList());
        List<String> list2 = list.stream().filter(s->s.startsWith("t_")).collect(Collectors.toList());
        System.out.println(list1);
        System.out.println(list2);
    }


    protected static class Calculate {
        public static Integer add20(Integer n){
            return n + 20;
        }
    }

    private static void effectivelyFinalExample(){
        int ekle = 1;
        List<Integer> list = new ArrayList<>();
        for(int i = 1;i<101;i++) list.add(new Integer(i));
        list.forEach(n-> {
            System.out.println(n+ekle);
        });
    }
}
