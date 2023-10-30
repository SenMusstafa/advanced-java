package com.etiya.campus.aj.util;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class OnlineInfoUtil {
    public SyndFeed getFeed(String url){
        SyndFeed result = null;
        SyndFeedInput input = null;
        try{
            URL source = new URL(url);
            input = new SyndFeedInput();
            result = input.build(new XmlReader(source));;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public List<String> getFeedList(String url){
        List<String> result = new ArrayList<>();
        SyndFeed feed = this.getFeed(url);
        if(feed!=null&&!feed.getEntries().isEmpty()){
            Iterator<SyndEntry> it = feed.getEntries().iterator();
            while(it.hasNext()){
                result.add(it.next().getLink());
            }
        }
        return result;
    }

    public void getWebPageData(String url){
        try{
            URL webPageUrl = new URL(url);
            ReadableByteChannel channel = Channels.newChannel(webPageUrl.openStream());
            try (InputStream inputStream = Channels.newInputStream(channel)) {
                ///channel.
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(Channels.newInputStream(channel)))) {
                String line;
                StringBuilder stringBuilder = new StringBuilder();
                while((line = br.readLine())!=null){
                    stringBuilder.append(line);
                    stringBuilder.append(System.lineSeparator());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getWebPageDataWithScanner(String url){
        try{
            URL webPageUrl = new URL(url);
            Scanner scanner = new Scanner(webPageUrl.openStream());
            StringBuffer stringBuffer = new StringBuffer();
            while(scanner.hasNext())
                stringBuffer.append(scanner.next());
            return stringBuffer.toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public void writeWebPageDataToFile(String url, String fileName){
        try{
            URL webPageUrl = new URL(url);
            ReadableByteChannel channel = Channels.newChannel(webPageUrl.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(fileName);
            fileOutputStream.getChannel().transferFrom(channel,0,Long.MAX_VALUE);
            fileOutputStream.close();
            channel.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
