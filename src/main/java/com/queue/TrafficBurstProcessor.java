package com.queue;

import java.util.Arrays;

public class TrafficBurstProcessor {

    public static int processAll(int[] arrivalTimes, int k) {
        if (arrivalTimes.length == 0) return 0;
        Arrays.sort(arrivalTimes); // 혹시 정렬 안 돼있을 수도 있으니

        int idx = 0;
        int n = arrivalTimes.length;
        int currentTime = arrivalTimes[0];
        int remainQueue = 0;
        int lastProcessedTime = 0;

        while (idx < n || remainQueue > 0) {
            // 현재 시간에 도착하는 요청 카운트
            int arrivedNow = 0;
            while (idx < n && arrivalTimes[idx] == currentTime) {
                arrivedNow++;
                idx++;
            }

            int totalRequests = remainQueue + arrivedNow;

            if (totalRequests > 0) {
                int processed = Math.min(totalRequests, k);
                remainQueue = totalRequests - processed;
                lastProcessedTime = currentTime;
            }

            currentTime++;
        }

        return lastProcessedTime;
    }

    public static void main(String[] args) {
        int[] arrival = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int lastTime = processAll(arrival, k);
        System.out.println("모든 요청 처리 완료 시각 = " + lastTime + "초"); // 3
    }
}
