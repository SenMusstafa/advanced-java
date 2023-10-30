package com.etiya.campus.aj.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class IOUtil {
    public InputStream convertReadertoInputStrream(Reader reader){
        InputStream result = null;
        try{
            char[] charBuffer = new char[8 * 1024];
            StringBuilder builder = new StringBuilder();
            int numCharsRead;
            while ((numCharsRead = reader.read(charBuffer, 0, charBuffer.length)) != -1) {
                builder.append(charBuffer, 0, numCharsRead);
            }
            result = new ByteArrayInputStream(
                    builder.toString().getBytes(StandardCharsets.UTF_8));

            reader.close();
            result.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
