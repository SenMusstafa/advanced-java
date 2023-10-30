package com.etiya.campus.aj.generics;

import java.util.ArrayList;
import java.util.List;

public class Varargs {
    public static void main(String[] args){

        printColors("Red","Green", "Blue");
        printColors("A","B");
    }

    private static void printColors(String... colors){
        for(int i=0; i<colors.length; i++){
            System.out.println(colors[i]);
        }
    }
}
