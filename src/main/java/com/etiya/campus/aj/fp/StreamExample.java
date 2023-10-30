package com.etiya.campus.aj.fp;


import com.etiya.campus.aj.util.OnlineInfoUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamExample {
    public static void main(String[] args){
        //ntStreamExample();

        //mapExample();
        //filterExample();
        //reduceExample();
        //sortExample();
        //generateExample();
        //rssExample();

    }

    private static void rssExample(){
        OnlineInfoUtil ru = new OnlineInfoUtil();
        List<String> links = ru.getFeedList("https://www.yahoo.com/news/rss");
        links.forEach(link -> {
            ru.writeWebPageDataToFile(link,"D:\\work\\Egitim\\advanced\\"+link.substring(link.lastIndexOf("/"),link.length()));
        });
    }

    private static void intStreamExample(){
        IntStream stream = IntStream.of(1,3,4,56,7,89,8);
        stream.forEach(System.out::println);

        IntStream stream1 = IntStream.generate(()->(int)(Math.random()*1000));
        stream1.limit(20).forEach(System.out::println);
    }

    private static void mapExample(){
        /*
        Map mevcut streame belirlenen fonksyonu uygulayıp yeni bir stream oluşturur
         */
        List<String> abcList = Stream.of("A","B","C","D").collect(Collectors.toList());
        List list1 = abcList.stream().map(String::toLowerCase).collect(Collectors.toList());
        System.out.println(list1);
        System.out.println(list1.stream().collect(Collectors.joining(" - ")));

        // map ile mesela objectconverter::toproduct gibi bir nesene listesinden başka nesne listesi oluşturma
    }

    private static void filterExample(){
        List<Integer> list = new ArrayList<>();
        for(int i = 1;i<101;i++) list.add(new Integer(i));
        list.stream().filter(i->i%3==0).forEach(System.out::println);
        list.stream().filter(i->i%3==0).map(i->i+1).forEach(System.out::println);
    }

    private static void reduceExample(){
        List<Integer> list = new ArrayList<>();
        for(int i = 1;i<101;i++) list.add(new Integer(i));
        System.out.println(list.stream().reduce(0,(toplam, sayi) -> toplam+sayi));

        List<String> abcList = Stream.of("A","B","C","D").collect(Collectors.toList());
        System.out.println(abcList.stream().reduce((s1,s2)->s1+"_"+s2));
    }

    private static void sortExample(){
        List<Integer> list = new ArrayList<>();
        for(int i = 1;i<101;i++) list.add(new Integer(i));
        Collections.sort(list,(a,b) -> b-a);
        System.out.println(list);
        //comparingint thencomparing
    }

    private static void iterateExample(){
        System.out.println(Stream.iterate(1, val ->val*1).limit(10).mapToInt(Integer::intValue).sum());
    }

    private static void generateExample(){
        Stream.generate(UUID::randomUUID).limit(10).forEach(System.out::println);
        Stream.generate(new AtomicInteger()::incrementAndGet).parallel()
                .limit(100).mapToInt(Integer::valueOf).max().ifPresent(System.out::println);
    }


}
