package com.etiya.campus.aj.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderExample {
    public static void main(String[] args){
        try{
            File dosya = new File("D:\\work\\Egitim\\advanced\\io\\Extreme_personalization_is_here.txt");
            FileReader fr = new FileReader(dosya);
            BufferedReader br = new BufferedReader(fr);
            String satir;
            while((satir = br.readLine())!=null){
                System.out.println(satir);
            }
            //br.lines().forEach(System.out::println);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
