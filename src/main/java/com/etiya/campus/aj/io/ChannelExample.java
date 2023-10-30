package com.etiya.campus.aj.io;

import com.etiya.campus.aj.db.ConnectionManager;
import com.etiya.campus.aj.db.dao.CustomerOrderDAO;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/*
Channel bir donanıma, dosyaya, network soketine ya da io işlemi yapan
 bir uygulamaya açılmış bağlantıyı temsil eder

 */
public class ChannelExample {
    public static void main(String[] args) throws IOException {
        //fileChannelExample1();
        asynchronousFileChannelExample3();
        //sysInOutExample();
        //scatterGatherExample();
        //byteBufferExample();
        //fileChannelExample3();
    }

    //socketchannel ekleyeyim mi?
/*
tek tek limit position vb nedir flip mlip, putta ne oluyor gette ne oluyor
breakpoint koyup da bakılabilir
 */
    private static void byteBufferExample1(){
        ByteBuffer buffer = ByteBuffer.allocate(2048);
        for(int i = 0;i<100;i++) buffer.put((byte) 6);
        while (buffer.hasRemaining()){
            System.out.println(buffer.get());
        }
    }
    private static void byteBufferExample(){
        ByteBuffer buffer = ByteBuffer.allocate(2048);
        System.out.println("position : "+buffer.position()+" limit : "+buffer.limit()+" capacity : "+buffer.capacity());
        System.out.println("put -----------------");
        for(int i = 0;i<100;i++) buffer.put((byte) 1);
        System.out.println("position : "+buffer.position()+" limit : "+buffer.limit()+" capacity : "+buffer.capacity());
        System.out.println("put iki -----------------");
        byte[] iki = new byte[200];
        Arrays.fill(iki,(byte) 2);
        buffer.put(iki);
        System.out.println("position : "+buffer.position()+" limit : "+buffer.limit()+" capacity : "+buffer.capacity());
        System.out.println("limit position flip -----------------");
        buffer.limit(buffer.position());
        buffer.position(200);
        buffer.flip();
        System.out.println("position : "+buffer.position()+" limit : "+buffer.limit()+" capacity : "+buffer.capacity());
        System.out.println("get -----------------");
        buffer.get(new byte[200]);
        System.out.println("position : "+buffer.position()+" limit : "+buffer.limit()+" capacity : "+buffer.capacity());
        System.out.println("compact -----------------");
        buffer.compact();
        System.out.println("position : "+buffer.position()+" limit : "+buffer.limit()+" capacity : "+buffer.capacity());
        System.out.println("put uc -----------------");
        byte[] uc = new byte[300];
        Arrays.fill(uc,(byte)3);
        buffer.put(uc);
        System.out.println("position : "+buffer.position()+" limit : "+buffer.limit()+" capacity : "+buffer.capacity());
        System.out.println("flip -----------------");
        buffer.flip();
        System.out.println("position : "+buffer.position()+" limit : "+buffer.limit()+" capacity : "+buffer.capacity());
    }

    private static void sysInOutExample(){
        try(ReadableByteChannel in = Channels.newChannel(System.in); WritableByteChannel out = Channels.newChannel(System.out)){
            ByteBuffer buffer = ByteBuffer.allocateDirect(2048);
            while(in.read(buffer)!=-1){
                buffer.flip();
                out.write(buffer);
                buffer.compact();
            }
            //buffer.flip();
            //while (buffer.hasRemaining())
            //    out.write(buffer);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static FileInputStream getFileInputStream(String file){
        FileInputStream result = null;
        try {
            result = new FileInputStream(file);
        }catch (IOException e){
            e.printStackTrace();
        }
        return result;
    }

    private static void fileChannelExample1(){
        try(RandomAccessFile  file = new RandomAccessFile("D:\\work\\Egitim\\advanced\\io\\Extreme_personalization_is_here.txt","r");
            FileChannel fileChannel = file.getChannel();
            ByteArrayOutputStream out = new ByteArrayOutputStream()){
            /*
            maplediğimiz kısmı memoryde locklamış oluyoruz
             */
            MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 6, 5);

            if (buffer.hasRemaining()) {
                byte[] data = new byte[buffer.remaining()];
                buffer.get(data);
                System.out.println(new String(data, StandardCharsets.UTF_8));
                out.write(data);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void fileChannelExample2(){
        try{
            FileOutputStream  out = new FileOutputStream( "D:\\work\\Egitim\\advanced\\io\\temp1.txt");
            FileChannel fileChannel = out.getChannel();
            String text = "Advanced Java eğitimi";

            ByteBuffer byteBuffer = ByteBuffer.wrap(text.getBytes());

            fileChannel.write(byteBuffer);
            byteBuffer.flip();
            //size metodu ile dosyanın byte olarak boyutunu alıyoruz
            System.out.println(fileChannel.size());

            //fileChannel = fileChannel.truncate(8);
            System.out.println(fileChannel.size());
            out.close();
            fileChannel.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static Map<String,String>  queryCustomerOrder(){
        ConnectionManager cm = new ConnectionManager();
        cm.init("D:\\work\\Egitim\\advanced\\db\\PSTNTESTProfile.txt");
        Connection connection = cm.getConnection(true);
        CustomerOrderDAO customerOrderDAO = new CustomerOrderDAO();
        customerOrderDAO.setTablePrefix(cm.getTablePrefix());
        Map<String,String> result = customerOrderDAO.getCustomerOrderInfo(connection,902013100710143361L);
        cm.closeConnection();
        return result;
    }

    private static void fileChannelExample3(){
        try{
            Set<StandardOpenOption> options = new HashSet<>();
            options.add(StandardOpenOption.CREATE);
            options.add(StandardOpenOption.APPEND);
            String text = "Advanced Java eğitimi";
            ByteBuffer byteBuffer = ByteBuffer.wrap(text.getBytes());
            Path path = Paths.get("D:\\work\\Egitim\\advanced\\io\\temp2.txt");
            FileChannel fileChannel = FileChannel.open(path, options);

            fileChannel.write(byteBuffer);
            fileChannel.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void asynchronousFileChannelExample1(){
        try{
            Path path = Paths.get("D:\\work\\Egitim\\advanced\\io\\Extreme_personalization_is_here.txt");
            AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            Future<Integer> operation = fileChannel.read(buffer, 0);
            while(!operation.isDone()){
                System.out.println("RUNNING");
                Thread.sleep(500);
            }
            System.out.println(operation.get());

            buffer.clear();
            fileChannel.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void asynchronousFileChannelExample2(){
        try{
            Path path = Paths.get("D:\\work\\Egitim\\advanced\\io\\Extreme_personalization_is_here.txt");
            AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            fileChannel.read(buffer, 0, null, new CompletionHandler<Integer,ByteBuffer>(){
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    System.out.println(result);
                }
                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    exc.printStackTrace();
                }
            });

            buffer.clear();
            fileChannel.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void asynchronousFileChannelExample3(){
        try{
            Path path = Paths.get("D:\\work\\Egitim\\advanced\\io\\temp3.txt");
            Files.createFile(path);
            Map<String,String> orderData = queryCustomerOrder();


            String text = orderData.entrySet().stream().map(e->"[ "+e.getKey()+" - "+e.getValue()+" ]").collect(Collectors.joining("|"));;
            AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);
            ByteBuffer buffer = ByteBuffer.wrap(text.getBytes());

            fileChannel.write(buffer,0,"Asenkron", new CompletionHandler<Integer,String>(){
                @Override
                public void completed(Integer result, String attachment) {
                    System.out.println(result);
                }
                @Override
                public void failed(Throwable exc, String attachment) {
                    exc.printStackTrace();
                }
            });

            buffer.clear();
            fileChannel.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /*
    ScatteringByteChannel ve GatheringByteChannel bir yerine birden fazla buffer
    kullanımı için geliştirilmiş. Birden fazla buffera dağıtıp geri topluyoruz
     */
    private static void scatterGatherExample(){
        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\work\\Egitim\\advanced\\io\\Extreme_personalization_is_here.txt");
            ScatteringByteChannel scatteringByteChannel = (ScatteringByteChannel)Channels.newChannel(fileInputStream);
            ByteBuffer buffer1 = ByteBuffer.allocateDirect(5);
            ByteBuffer buffer2 = ByteBuffer.allocateDirect(4);
            ByteBuffer[] buffers = {buffer1, buffer2};
            scatteringByteChannel.read(buffers);
            buffer1.flip();
            while(buffer1.hasRemaining())
                System.out.println(buffer1.get());
            System.out.println("-----------------");
            buffer2.flip();
            while(buffer2.hasRemaining())
                System.out.println(buffer2.get());
            buffer1.rewind();
            buffer2.rewind();
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\work\\Egitim\\advanced\\io\\gather.txt");
            GatheringByteChannel gatheringByteChannel = (GatheringByteChannel)Channels.newChannel(fileOutputStream);
            buffers[0] = buffer2;
            buffers[1] = buffer1;
            gatheringByteChannel.write(buffers);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
