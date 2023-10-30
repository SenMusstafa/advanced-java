package com.etiya.campus.aj.ff;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LockFileExample {
    public static void main(String[] args){
        try{
            Path path = Paths.get("D:\\work\\urunler.txt");
            FileChannel channel = FileChannel.open(path);
            FileLock  lock1 = channel.lock();
            channel.close();

            Path path1 = Paths.get("D:\\work\\urunler_kopya.txt");
            FileChannel channel1 = FileChannel.open(path1);
            FileLock  lock2 = channel1.tryLock();
            channel1.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
