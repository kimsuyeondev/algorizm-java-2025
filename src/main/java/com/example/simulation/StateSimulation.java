package com.example.simulation;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
/*
각 줄은 "HH:MM driverId STATUS" 형식이다.
STATUS 는 ONLINE, IDLE, BUSY, OFFLINE 중 하나라고 가정한다.
같은 driverId에 대해 여러 번 로그가 있을 수 있다.
마지막 상태가 ONLINE 또는 IDLE인 드라이버만 골라서,
driverId 사전순(오름차순)으로 정렬해 배열로 반환하라.
* */
/**
 * @param  "HH:MM driverId STATUS" 형식의 문자열 배열
 * @return 마지막 상태가 ONLINE 또는 IDLE 인 driverId를 사전순 정렬해 반환
 */
public class StateSimulation {

    public static void main(String[] agrs) {
        String[] log = {"10:00 driver1 ONLINE",
                "10:05 driver1 BUSY",
                "10:10 driver2 ONLINE",
                "10:20 driver1 IDLE",
                "10:30 driver2 BUSY",
                "10:40 driver3 ONLINE"};
        Solution solution = new Solution();
        // "HH:MM driverId STATUS"
        System.out.println(Arrays.toString(solution.solution(log)));
        System.out.println();
    }
}

class Solution {
    public String[] solution(String[] logs) {
        Map<String, String> driverMap = new HashMap<>();
        for (String line : logs) {
            String[] parts = line.split(" ");
            //    String time = parts[0];
            String driverId = parts[1];
            String status = parts[2];
            driverMap.put(driverId, status);
        }
        List<String> resultList = driverMap.entrySet().stream()
                .filter(map -> "ONLINE".equals(map.getValue()) || "IDLE".equals(map.getValue()))
                .map(map -> map.getKey())
                .sorted()
                .collect(Collectors.toList());
             //resultList.sort((a,b)-> a.compareTo(b));


        // 마지막 상태가 ONLINE 또는 IDLE인 드라이버만 골라서,
        // driverId 사전순(오름차순)으로 정렬해 배열로 반환하라.
        return resultList.toArray(new String[]{});
    }
}
