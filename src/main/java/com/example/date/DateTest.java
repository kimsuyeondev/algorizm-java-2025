package com.example.date;

public class DateTest {

    static int[] days = {0, 31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31};

    //윤년여부 태양오차로 29일이 있는 년도 여부
    static boolean isLeap(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    //0000-01-01 부터 몇일이 지났는지
    static long toDays(int year, int month, int day) {
        long result = day;
        for (int y = 1; y < year; y++) {
            result += isLeap(y) ? 366 : 365;
        }
        for (int m = 1; m < month; m++) {
            if(m == 2 && isLeap(year)) {
                result += 29;
            }else {
                result += days[m];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("?"+toDays(1,1,2));


        //두날짜의 차이 2025-11-23 ~ 2024-11-23
        long diffDays = toDays(2025,11,23) - toDays(2025,11,20);
        System.out.println(diffDays);
        //문자열 파싱
        String date = "2025-11-23";
        String[] arr = date.split("-");
        int y = Integer.parseInt(arr[0]);
        int m = Integer.parseInt(arr[1]);
        int d = Integer.parseInt(arr[2]);
    }
}

