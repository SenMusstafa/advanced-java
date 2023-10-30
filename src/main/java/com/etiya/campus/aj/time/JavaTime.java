package com.etiya.campus.aj.time;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalQueries;

public class JavaTime {
    public static void main(String[] args){
        Instant now = Instant.now();
        System.out.println(now);
        //LocalDateTime.now().query(time-> {LocalTime control = LocalTime.from(time)); return control.getHour()>17;});
        TemporalQueries.chronology();//??
        LocalDate localDate = LocalDate.now();
    }
}
