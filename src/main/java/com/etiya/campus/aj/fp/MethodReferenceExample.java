package com.etiya.campus.aj.fp;

import com.etiya.campus.aj.common.Product;
import com.etiya.campus.aj.domain.validation.GenericValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MethodReferenceExample {
    public static void main(String[] args){
        //Product product = new Product("Ev Avantaj 300");
        List<String> list = new ArrayList<>();
        list.add("A");list.add("B");list.add("C");
        List list1 = list.stream().map(String::toLowerCase).collect(Collectors.toList());
        List list2 = list.stream().map(s->s.toLowerCase()).collect(Collectors.toList());
        GenericValidator gv = new GenericValidator();
        List list3 = list.stream().map(gv::toLoweCase).collect(Collectors.toList());
        list3.forEach(System.out::println);
        System.out.println(list1);
        System.out.println(list3);
    }
}
