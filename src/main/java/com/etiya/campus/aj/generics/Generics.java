package com.etiya.campus.aj.generics;

import java.util.ArrayList;
import java.util.List;

public class Generics {
    public static void main(String[] args){
        //Generics olmadan
        List colors = new ArrayList();
        colors.add(0);
        colors.add("Red");
        String color = (String) colors.get(0);
        System.out.println("Color : "+color);

        //Generics kullanarak
        List<String> genericColors = new ArrayList<String>();
        genericColors.add("Red");
        String genericColor = genericColors.get(0);
        System.out.println("Color : "+genericColor);
    }

    public static void keyValues(){
        KeyValuePair<String,Integer> kv1 = new KeyValuePair<>("Bir",1);
        KeyValuePair<Integer,String> kv2 = new KeyValuePair<>(1, "Bir");
        System.out.println("Key1 :"+kv1.getKey() +" - Value " +kv1.getValue());
        System.out.println("Key2 :"+kv2.getKey() +" - Value " +kv1.getValue());
    }
}
