package com.example.date;

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
class SolutionPracticeTestDday {
    static int[] MONTH_TO_DAYS = {0,
            31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31
    };

    //윤년여부 2024년은 윤년이다.
    boolean isLeap(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    //0000년 1월 1일 부터 몇일이 지났는지 계산
    int toDays(int year, int month, int day) {
        int result = day;
        //year
        for(int y = 2024; y < year; y++) {
            result += isLeap(y) ? 366 : 365;
        }

        //month
        for(int m = 1; m < month; m++) {
            result += MONTH_TO_DAYS[m];
            if (m == 2 && isLeap(year)) {
                result += 1;
            }
        }
        return result;
    }

    int[] parse(String[] date1) {
        int year = Integer.parseInt(date1[0]);
        int month = Integer.parseInt(date1[1]);
        int day = Integer.parseInt(date1[2]);
        return new int[]{year, month, day};
    }

    public String solution(String date1, String date2) {
        int[] parts = parse(date1.split("-"));
        int[] target = parse(date2.split("-"));
        int diff = toDays(target[0], target[1], target[2]) - toDays(parts[0], parts[1], parts[2]);

        if (diff == 0) {
            return "D-0";
        } else if (diff > 0) {   // target이 미래
            return "D-" + diff;
        } else {                 // target이 과거
            return "D+" + (-diff);
        }
    }
}

public class PracticeTestDday {
    public static void main(String[] args) {
        /**
         * today = "2024-02-27", target = "2024-03-01" → "D-3"
         * today = "2024-03-01", target = "2024-03-01" → "D-0"
         * today = "2024-03-05", target = "2024-03-01" → "D+4" (지나간 날짜)
         * */
        SolutionPracticeTestDday solution = new SolutionPracticeTestDday();
        System.out.println(solution.solution("2024-02-27", "2024-03-01"));
        System.out.println(solution.solution("2024-03-01", "2024-03-01"));
        System.out.println(solution.solution("2024-01-02", "2024-03-01"));
        System.out.println(solution.solution("2024-03-05", "2024-03-01"));
        System.out.println(solution.solution("2024-01-31", "2024-03-01"));
        System.out.println(solution.solution("2025-01-01", "2026-01-03"));
    }
}
