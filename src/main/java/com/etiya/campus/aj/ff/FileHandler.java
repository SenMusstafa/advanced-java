package com.etiya.campus.aj.ff;

import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileHandler {
    private Scanner scanner;

    public FileHandler(String fileName) {
        try{
            this.scanner =  new Scanner(Paths.get(fileName), StandardCharsets.UTF_8.name());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public String getLine(){
        if(this.scanner.hasNextLine())
            return this.scanner.nextLine();
        else
            return null;
    }

    public boolean hasNextLine(){
        return this.scanner.hasNextLine();
    }
}
