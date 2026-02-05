package com.example.date;

public class DriveLogDuration {

    // "HH:MM" → 분 단위
    private static int toMinutes(String time) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        return hour * 60 + minute;
    }

    // "START 09:10", "END 10:00" 같은 로그 배열에서 총 운행 시간(분) 계산
    public static int totalDriveMinutes(String[] logs) {
        int total = 0;
        Integer currentStart = null;

        for (String log : logs) {
            String[] parts = log.split(" ");
            String type = parts[0];
            String time = parts[1];

            if (type.equals("START")) {
                currentStart = toMinutes(time);
            } else if (type.equals("END") && currentStart != null) {
                int end = toMinutes(time);
                total += (end - currentStart);
                currentStart = null;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        String[] logs = {
                "START 09:00",
                "END 09:30",
                "START 10:00",
                "END 10:45"
        };
        System.out.println("총 운행 시간(분): " + totalDriveMinutes(logs)); // 75
    }
}
