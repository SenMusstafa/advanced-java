package com.etiya.campus.aj.ff;

import java.io.File;

public class DirectoryExample {
    public static void main(String[] args){
        String[] dizindekiler = new File(".").list();
        for(String dosya: dizindekiler){
            System.out.println(dosya);
        }
    }
}
