package com.etiya.campus.aj.io;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkingExample {
    public static void main(String[] args){
        inetAddressExample();
    }

    private static void inetAddressExample(){
        try {
            InetAddress etiyaAddress = InetAddress.getByName("www.etiya.com");
            InetAddress loopback = InetAddress.getByName(null);
            InetAddress localhost = InetAddress.getLocalHost();

            System.out.println("Etiya : "+etiyaAddress.getHostAddress());
            System.out.println("Loopback : "+loopback.getHostAddress());
            System.out.println("Localhost : "+localhost.getHostAddress());
        }catch (UnknownHostException e){
            e.printStackTrace();
        }
    }
}
