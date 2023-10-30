package com.etiya.campus.aj.io;

import java.io.IOException;
import java.nio.channels.Selector;

/*
Selector birden fazla channelı yönetir.
Hangilerinin yazma okuma bağlantıyı tamamlama
yeni bağlantı kabul etme gibi görevleri için uygun olduğunu izler.
SelectableChannel olan channellar ile kullanılır.
 */
public class SelectorExample {
    private static void main(String[] args){
        try{
            Selector selector = Selector.open();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
