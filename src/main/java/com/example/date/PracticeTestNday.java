package com.example.date;

/**
 * 함수 solution을 구현하라.
 * 시작 날짜 date와 정수 n이 주어질 때,
 * date에서 n일 뒤의 날짜를 "YYYY-MM-DD" 형태로 반환하라.
 * date 형식: "YYYY-MM-DD"
 * 1900-01-01 <= date <= 2100-12-31
 * 0 <= n <= 1_000_000 (백만)
 * 윤년 규칙은 동일:
 * (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
 * 예시:
 * date = "2024-02-27", n = 3 → "2024-03-01"
 * date = "2024-02-28", n = 1 → "2024-02-29" (윤년)
 * date = "2024-12-31", n = 1 → "2025-01-01"
 * date = "2100-02-28", n = 1 → "2100-03-01" (2100년은 평년)
 */
class SolutionPracticeTestNday {
    static int[] MONTH_TO_DAYS = {0,
            31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31
    };

    //윤년여부 2024년은 윤년이다.
    boolean isLeap(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    int getDayInMonth(int year, int month) {
        int day = MONTH_TO_DAYS[month];

        if (isLeap(year) && month == 2) {
            day = 29;
        }
        return day;
    }

    public String solution(String date, int n) {
        String[] parts = date.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);

        for (int i = 0; i < n; i++) {
            day++;

            if (day > getDayInMonth(year,month)) {
                day = 1;
                month++;
            }

            if (month > 12) {
                year += 1;
                month = 1;
            }
        }

        return String.format("%04d-%02d-%02d", year, month, day);
    }
}
public class PracticeTestNday {
    public static void main(String[] args) {
        SolutionPracticeTestNday solution = new SolutionPracticeTestNday();
        System.out.println(solution.solution("2024-02-27", 3));
        System.out.println(solution.solution("2024-01-01", 10));
        System.out.println(solution.solution("2024-01-31", 1));
        System.out.println(solution.solution("1900-12-31", 1));
        System.out.println(solution.solution("1900-12-31", 2));
        System.out.println(solution.solution("1900-01-31", 3));
    }
}

