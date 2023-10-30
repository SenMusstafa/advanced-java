package com.etiya.campus.aj.mutlithreading;

import com.etiya.campus.aj.common.Phone;

/*
adını değiştir
stacktrace çıktısı

bir meto çalıştığında metod değişkenleri stack üzerinde tanımlanır tamamlanınca temizlenir.
bunlara sadece ilgili metod erişenbilir
lifo prensibi ile değerlere hızlı erişim sağlanır
 */
public class MemoryExample {
    /*
    instance variable, tanımnlandığı nesneye aittir onun içinde tanımlıdır.
     */
    private String str = "";
    public static void main(String[] args){
        /*
        Bütün nesneler heap içinde yre alır
         */
        Phone p1 = new Phone("Iphone");
        // Phone p1  referans
        // new Phone("Iphone"); nesnenin kendisi
        //Phone p1 = new Phone("Iphone"); referans nesneye bağlanır

        /*
        metod çağrıları ve lokal değişkenler stack içinde yer alırlar
         */
        method1();
        // iç içe 3 metod çağırıp değişkenlerinin döngüsünü anlat

        /*
        nesneler heap alanıunda oluşturulur
        young generation - -> yeni neslener buraya atanır
        old/ tenured generation uzun süre yaşamış nesneleer buraya alınır
        ygde belirli süreyi geçen ,yaşlanan nesneler taşınır
        permanent generation jvm metadata runtime classlar
         */
    }

    private static void method1(){
        /*
        yerel değişken ler metod içinde tanımlıdır, tamamlanana kadar stack içinde yer alırlar
         */
        int i = 0;
        /*
        nesne tanımlanırsa nesne heapdedir local değişken stackdedir nesne referansıdır kendisi değil
         */
    }
}
