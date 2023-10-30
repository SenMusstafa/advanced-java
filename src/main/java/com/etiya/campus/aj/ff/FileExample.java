package com.etiya.campus.aj.ff;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.spi.FileSystemProvider;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileExample {
    public static void main(String[] args){
        //readFileExample();
        listFileSystemProviders();
    }
    private static void listFileSystemProviders(){
        List<FileSystemProvider> providers = FileSystemProvider.installedProviders();
        providers.forEach(System.out::println);
        FileSystem defaultFs = FileSystems.getDefault();
        defaultFs.supportedFileAttributeViews().forEach(System.out::println);
        Path path = defaultFs.getPath("");
    }

    private static void readFileExample(){
        Path path = Paths.get("D:\\work\\Egitim\\advanced\\io\\Extreme_personalization_is_here.txt");
        Pattern noktalama = Pattern.compile("\\p{Punct}");
        Pattern bosluk = Pattern.compile("\\s+");
        Pattern kelimeler = Pattern.compile("\\w+");
        try (Stream<String> stream = Files.lines(path)){
            //.peek(System.out::println)
            long adim1 = System.currentTimeMillis();
            //[A,s,s,[r,t,y],9,[u,t,r],o]
            //[A,s,s,r,t,y,9,u,t,r,o]
            Map<String, Integer> kelimeSayisi = stream.map(noktalama::matcher).map(matcher -> matcher.replaceAll(""))
                    .map(bosluk::split).flatMap(Arrays::stream).map(String::toLowerCase)
                    .collect(Collectors.toMap(Function.identity(),kelime -> 1, Integer::sum));
            long adim2 = System.currentTimeMillis();
            System.out.println(kelimeSayisi);
            System.out.println("sure : "+(adim2-adim1));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
