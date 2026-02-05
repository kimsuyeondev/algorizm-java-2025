package com.practice.parsing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  감성 문자열 파싱 문제
 * - "START,driverA,09:00"
 * - "END,driverA,09:30"
 * 이런 로그를 파싱해서
 * 기사별 (완료 운행 건수, 총 운행 시간(분))을 계산.
 */
public class DriveLogParser {

    // "HH:MM" -> 분단위 정수로 변환
    private static int toMinutes(String time) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        return hour * 60 + minute;
    }

    // 로그 배열을 받아 기사별 운행 집계
    public static Map<String, int[]> aggregate(String[] logs) {
        // driverId -> [totalTrips, totalMinutes]
        Map<String, int[]> result = new HashMap<>();

        // driverId -> 현재 진행 중인 운행의 시작 시각(분)
        Map<String, Integer> ongoingStart = new HashMap<>();

        for (String log : logs) {
            // "TYPE,DRIVER_ID,HH:MM"
            String[] parts = log.split(",");
            String type = parts[0];       // START or END
            String driverId = parts[1];
            String timeStr = parts[2];

            int time = toMinutes(timeStr);

            if (type.equals("START")) {
                // 아직 진행중 운행이 없을 때만 START로 등록
                // (이상 로그는 무시할 수 있음)
                if (!ongoingStart.containsKey(driverId)) {
                    ongoingStart.put(driverId, time);
                }
            } else if (type.equals("END")) {
                // END는 반드시 START가 있었을 때만 처리
                if (ongoingStart.containsKey(driverId)) {
                    int startTime = ongoingStart.remove(driverId);
                    int duration = time - startTime;
                    if (duration < 0) {
                        // 시간 꼬인 이상 로그면 무시 (필요 시 방어 코드)
                        continue;
                    }

                    // 집계 배열 가져오기 (없으면 {0,0}으로 초기화)
                    int[] stat = result.getOrDefault(driverId, new int[2]);
                    stat[0] += 1;         // totalTrips 증가
                    stat[1] += duration;  // totalMinutes 누적
                    result.put(driverId, stat);
                }
            }
        }

        // 미완료 운행(START만 있고 END 없는 것)은 무시되었음
        return result;
    }

    public static void main(String[] args) {
        String[] logs = {
                "START,driverA,09:00",
                "END,driverA,09:30",
                "START,driverA,10:00",
                "END,driverA,10:45",
                "START,driverB,09:15",
                "END,driverB,09:45",
                "START,driverA,10:00",
                "END,driverA,10:10",
                "START,driverA,11:00" // END 없음 -> 무시
        };

        Map<String, int[]> result = aggregate(logs);

        // 기사 ID 사전순 정렬 출력
        List<String> driverIds = new ArrayList<>(result.keySet());
        Collections.sort(driverIds);

        for (String driverId : driverIds) {
            int[] stat = result.get(driverId);
            int totalTrips = stat[0];
            int totalMinutes = stat[1];
            System.out.println(driverId + "," + totalTrips + "," + totalMinutes);
        }
        // 기대 출력:
        // driverA,2,75
        // driverB,1,30
    }
}
