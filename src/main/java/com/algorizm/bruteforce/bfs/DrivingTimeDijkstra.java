package com.algorizm.bruteforce.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
/*
주행 시간 가중치 – 도로 그래프에서 최소 주행 시간 (다익스트라)
도로가 여러 개 있고, 각 도로마다 “주행 시간”이 다를 때
출발지 → 도착지까지 최소 시간 구하는 버전
문제 설정
교차로 개수: N (0 ~ N-1)
도로 목록: roads[i] = {from, to, time}

모든 도로는 양방향이라고 가정

start에서 end까지 최소 주행 시간 구하기
* */
public class DrivingTimeDijkstra {

    // n: 교차로 수 (0 ~ n-1)
    // roads: {from, to, time} 리스트
    // start → end 까지 최소 주행 시간
    public static int minDrivingTime(int n, int[][] roads, int start, int end) {

        // 인접 리스트: graph[node] = { {nextNode, time}, ... }
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            int from = road[0];
            int to = road[1];
            int time = road[2];

            // 양방향 도로라고 가정
            graph[from].add(new int[]{to, time});
            graph[to].add(new int[]{from, time});
        }

        // dist[node] = start에서 node까지의 최소 주행 시간
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // PQ에는 {node, totalTime} 저장
        PriorityQueue<int[]> priorityQueue =
                new PriorityQueue<>((a, b) -> a[1] - b[1]);

        dist[start] = 0;
        priorityQueue.offer(new int[]{start, 0});

        while (!priorityQueue.isEmpty()) {
            int[] cur = priorityQueue.poll();
            int node = cur[0];
            int totalTime = cur[1];

            // 이미 더 좋은 경로가 있으면 스킵
            if (totalTime > dist[node]) continue;

            // 도착하면 바로 리턴 (현재가 최소)
            if (node == end) {
                return totalTime;
            }

            // 인접 도로들 탐색
            for (int[] edge : graph[node]) {
                int next = edge[0];
                int driveTime = edge[1];

                int newTime = totalTime + driveTime;

                if (newTime < dist[next]) {
                    dist[next] = newTime;
                    priorityQueue.offer(new int[]{next, newTime});
                }
            }
        }

        return -1; // 도달 불가
    }

    public static void main(String[] args) {
        // 교차로 5개 (0~4)
        int n = 5;
        int[][] roads = {
                {0, 1, 5},   // 0↔1 : 5분
                {1, 2, 3},   // 1↔2 : 3분
                {0, 3, 10},  // 0↔3 : 10분
                {3, 4, 1},   // 3↔4 : 1분
                {2, 4, 2}    // 2↔4 : 2분
        };

        int start = 0;
        int end = 4;

        int result = minDrivingTime(n, roads, start, end);
        System.out.println("최소 주행 시간: " + result); // 예: 5+3+2 = 10
    }
}
