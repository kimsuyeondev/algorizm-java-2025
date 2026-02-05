package com.practice.date;

import java.util.HashSet;
import java.util.Set;

/**
 * 패턴 A. 기준일 기준으로 D-day 문자열 반환
 * <p>
 * 오늘 날짜 today와 어떤 날짜 target이 주어질 때,
 * today 기준으로 며칠 남았는지/지났는지를
 * "D-3", "D+2", "D-0" 형태로 반환하라.
 * (항상 YYYY-MM-DD 형식)
 * <p>
 * today = "2024-02-27", target = "2024-03-01" → "D-3"
 * today = "2024-03-01", target = "2024-03-01" → "D-0"
 * today = "2024-03-05", target = "2024-03-01" → "D+4" (지나간 날짜)
 */
class SolutionDdayCalculator {
    static int[] MONTH_TO_DAYS = {0,
            31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31
    };

    //윤년여부 2024년은 윤년이다.
    boolean isLeap(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    //1900년 1월 1일 부터 몇일이 지났는지 계산
    int toDays(int year, int month, int day) {
        int result = day;
        //year
        for(int y = 1900; y < year; y++) {
            result += isLeap(y) ? 366 : 365;
        }

        //month
        for(int m = 1; m < month; m++) {
            result += daysInMonth(year,m);
        }
        return result;
    }

    // 해당 연도/월의 실제 일수
    private int daysInMonth(int year, int month) {
        int days = MONTH_TO_DAYS[month];
        if (month == 2 && isLeap(year)) {
            days++;
        }
        return days;
    }

    int[] parse(String[] date1) {
        int year = Integer.parseInt(date1[0]);
        int month = Integer.parseInt(date1[1]);
        int day = Integer.parseInt(date1[2]);
        return new int[]{year, month, day};
    }

    void nextDay(int[] parts) {
        int year = parts[0];
        int month = parts[1];
        int day = parts[2];

        day++;
        int lastDay = daysInMonth(year, month);


        if(day > lastDay) {
            day = 1;
            month++;
        }

        if(month > 12) {
            year++;
            month = 1;
        }

        parts[0] = year;
        parts[1] = month;
        parts[2] = day;
    }

    public long solution(String startDate,
                         String endDate,
                         int weekdayFee,
                         int weekendFee,
                         String[] holidays) {
        int[] parts = parse(startDate.split("-"));
        int[] target = parse(endDate.split("-"));
        //int diff = toDays(target[0], target[1], target[2]) - toDays(parts[0], parts[1], parts[2]);

        //공휴일
        Set<String> holidaySet = new HashSet<>();
        for(String holiday : holidays) {
            holidaySet.add(holiday);
        }
        long totalFee = 0;

        while (true) {
            String curStr = String.format("%04d-%02d-%02d", parts[0],parts[1],parts[2]);
            System.out.println(curStr);

            if(holidaySet.contains(curStr)) {
                totalFee += weekendFee;
            }else {

                int weekendIndex = (toDays(parts[0], parts[1], parts[2]) - 1) % 7; //1900년1월1일이 월요일 0

                if (weekendIndex == 6 || weekendIndex == 5) {
                    totalFee += weekendFee;
                } else {
                    totalFee += weekdayFee;
                }
            }
            if(target[0] == parts[0] && target[1] == parts[1] && target[2] == parts[2]) {
                break;
            }

            nextDay(parts);
        }
        return  totalFee;
    }
}

/**
 * @param startDate  멤버십 시작일 (YYYY-MM-DD, 포함)
 * @param endDate    멤버십 종료일 (YYYY-MM-DD, 포함, 항상 startDate <= endDate)
 * @param weekdayFee 평일 1일 요금
 * @param weekendFee 주말/공휴일 1일 요금
 * @param holidays   공휴일 날짜 목록 (YYYY-MM-DD 배열, 없을 수도 있음)
 * @return           전체 기간 동안의 총 요금
 */
public class DdayCalculator {
    public static void main(String[] args) {
        SolutionDdayCalculator solution = new SolutionDdayCalculator();
        System.out.println(solution.solution("2025-01-01", "2025-01-31", 1000, 2000, new String[] {"2025-01-01"}));
        System.out.println(solution.solution("2024-01-31", "2024-03-01", 1000, 2000, new String[] {"2025-01-31"}));

    }
}



