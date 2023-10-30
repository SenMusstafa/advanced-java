package com.etiya.campus.aj.ff;

import java.io.File;
import java.io.IOException;

public class CreateFileExample {
    public static void main(String[] args){
        try{
            File yeniDosya = new File("D:\\work\\urunler.txt");
            yeniDosya.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
