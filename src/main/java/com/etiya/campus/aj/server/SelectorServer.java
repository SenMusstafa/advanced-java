package com.etiya.campus.aj.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class SelectorServer {
    final static int DEFAULT_PORT = 7777;

    static ByteBuffer buffer = ByteBuffer.allocateDirect(8);

    public static void main(String[] args) throws IOException{
        System.out.println("Server start on port : "+ DEFAULT_PORT);

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(DEFAULT_PORT));
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while(true){
            int n = selector.select();
            if(n==0) continue;
            Iterator it = selector.selectedKeys().iterator();
            while(it.hasNext()){
                SelectionKey key = (SelectionKey) it.next();
                if(key.isAcceptable()){
                    SocketChannel socketChannel = ((ServerSocketChannel) key.channel()).accept();
                    if(socketChannel == null) continue;
                    System.out.println("Receiving connection");
                    buffer.clear();
                    buffer.putLong(System.currentTimeMillis());
                    buffer.flip();
                    System.out.println("Writing current time");
                    while ((buffer.hasRemaining()))
                        socketChannel.write(buffer);
                    serverSocket.close();
                }
                it.remove();
            }
        }
    }
}
