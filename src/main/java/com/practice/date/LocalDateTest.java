package com.practice.date;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class LocalDateTest {


    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = LocalDate.of(2025,8,22);

        LocalTime localTime = LocalTime.now();
        LocalTime localTime1 = LocalTime.of(23,22,20,1);
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.of(2020,11,23,10,23,22);

        System.out.println(localDate1.isBefore(localDate));
        System.out.println(localDate1.isAfter(localDate));
        System.out.println(localDateTime.toLocalDate());
        System.out.println(localDateTime.toLocalTime());

        System.out.println(localDate.minusDays(3));
        System.out.println(localDate.plusDays(3));

        //Period period = Period.between(localDate1,localDate);
        //System.out.println(period.getYears());
        //System.out.println(period.getMonths());
        //두날짜의 차이
        System.out.println(ChronoUnit.DAYS.between(localDateTime,localDateTime1));
        //요일
        DayOfWeek day = localDateTime.getDayOfWeek();
        DayOfWeek day1 = localDate.getDayOfWeek();
        System.out.println(day + " "+day1);

    }
}
