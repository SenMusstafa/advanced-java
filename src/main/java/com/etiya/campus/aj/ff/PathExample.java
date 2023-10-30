package com.etiya.campus.aj.ff;

import javax.sql.rowset.Predicate;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

public class PathExample {
    public static void main(String[] args){
        example1();
        //example2();
        //example3();
    }

    private static void example1(){
        try{
            Path path = Paths.get("D:\\work\\silinecek.txt");
            Files.deleteIfExists(path);

            Path path2 = Paths.get("D:\\work\\urunler.txt");
            System.out.println(path2.getParent());
            System.out.println(path2.getRoot());
            System.out.println(path2.getFileName());

            Path path3 = Paths.get("D:\\work\\urunler_kopya.txt");
            Files.copy(path2,path3);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void example2(){
        Path path = Paths.get("D:\\work\\Egitim\\advanced");
        try(Stream<Path> stream = Files.walk(path)){
            stream.map(Path::toFile).filter(File::isFile).sorted().forEach(System.out::println);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void example3(){
        Path path = Paths.get("D:\\work\\Egitim\\advanced");
        BiPredicate<Path, BasicFileAttributes> matcher = (p,attributes) -> attributes.isDirectory();
        try(Stream<Path> stream = Files.find(path,Integer.MAX_VALUE, matcher)){
            stream.sorted().forEach(System.out::println);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
