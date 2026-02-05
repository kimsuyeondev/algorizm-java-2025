package com.example.date;

import java.util.Arrays;

/*
함수 solution을 구현하라.
두 날짜 date1, date2가 문자열로 주어질 때,
date1부터 date2 전날까지의 일 수 차이를 반환하라.
        (즉, date2 - date1 의 일 수. 항상 date1 <= date2 가정)
날짜 형식은 항상 "YYYY-MM-DD" 이다.
        연도는 1900 이상 2100 이하라고 가정해도 좋다.
윤년 규칙은 그냥 진짜 달력 규칙 그대로:
        (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0) 이면 윤년
예를 들어:
        "2024-01-01" 과 "2024-01-02" → 1
        "2024-01-01" 과 "2024-01-31" → 30
        "2024-02-27" 과 "2024-03-01" → 3 (2024는 윤년이라 2월 29일까지 있음)*/
public class PracticeTest1 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.diffDays("1900-01-01", "1900-01-02")); // 1
        System.out.println(solution.diffDays("2024-01-01", "2024-01-02")); // 1
        System.out.println(solution.diffDays("2024-01-01", "2024-01-31")); // 30
        System.out.println(solution.diffDays("1900-01-01", "1901-01-01")); // 365
        System.out.println(solution.diffDays("1903-01-01", "1905-01-01")); // 731 (1904 윤년)
    }

}

class Solution {

    boolean isLeap(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    static int[] MONTH_TO_DAYS = {0,
            31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31};

    //
    int toDays(int year, int month, int day) {
        int result = day;
        for (int i = 1900; i < year; i++) {
            result += isLeap(i) ? 366 : 365;
        }

        for (int i = 1; i < month; i++) {
            result += getDaysInMonth(year, i);
        }
        System.out.println(result);
        return result;
    }

    private int getDaysInMonth(int year, int m) {
        int result = MONTH_TO_DAYS[m];
        if (isLeap(year) && m == 2) {
            result += 1;
            return result;
        }
            return result;
    }

    public int diffDays(String date1, String date2) {
        int[] date1Array = Arrays.stream(date1.split("-")).mapToInt(Integer::parseInt).toArray();
        int[] date2Array = Arrays.stream(date2.split("-")).mapToInt(Integer::parseInt).toArray();

        int toDaysDate1 = toDays(date1Array[0], date1Array[1], date1Array[2]);
        int toDaysDate2 = toDays(date2Array[0], date2Array[1], date2Array[2]);

        return toDaysDate2 - toDaysDate1;
    }
}
