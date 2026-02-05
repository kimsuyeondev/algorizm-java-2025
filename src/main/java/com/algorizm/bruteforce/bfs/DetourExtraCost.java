package com.algorizm.bruteforce.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
/**
우회 시 비용 계산 – 특정 도로가 막혔을 때 추가로 드는 시간
 원래는 최단 경로로 가다가,
 어떤 도로가 “사고/통제”로 막혔다고 가정했을 때
 우회하면 얼마나 더 걸리는지(추가 비용) 계산
 💡 문제 설정
 위와 동일한 도로 그래프
 (blockFrom, blockTo) 도로가 막혔다고 가정
 1단계: 원래 최소 시간 = original
 2단계: 해당 도로 제외하고 다시 다익스트라 = detour
 추가 비용: detour - original
 * */
public class DetourExtraCost {

    // 공통 다익스트라 함수 (특정 도로를 제외할 수 있게 함)
    private static int dijkstraWithBlockedEdge(int n,
                                               int[][] roads,
                                               int start,
                                               int end,
                                               int blockFrom,
                                               int blockTo) {
        // 인접 리스트 생성
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            int from = road[0];
            int to = road[1];
            int time = road[2];

            // blockFrom - blockTo 도로는 제외
            boolean isBlocked = (from == blockFrom && to == blockTo)
                    || (from == blockTo && to == blockFrom);

            if (isBlocked) {
                continue;
            }

            graph[from].add(new int[]{to, time});
            graph[to].add(new int[]{from, time});
        }

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> priorityQueue =
                new PriorityQueue<>((a, b) -> a[1] - b[1]); // {node, totalTime}

        dist[start] = 0;
        priorityQueue.offer(new int[]{start, 0});

        while (!priorityQueue.isEmpty()) {
            int[] cur = priorityQueue.poll();
            int node = cur[0];
            int totalTime = cur[1];

            if (totalTime > dist[node]) continue;
            if (node == end) return totalTime;

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

        return Integer.MAX_VALUE; // 도달 불가 상황
    }

    // 우회 시 추가로 드는 시간 계산
    public static int extraDetourTime(int n,
                                      int[][] roads,
                                      int start,
                                      int end,
                                      int blockFrom,
                                      int blockTo) {

        // 1) 원래 최소 주행 시간
        int original = dijkstraWithBlockedEdge(n, roads, start, end,
                -1, -1); // block 없음

        // 2) 특정 도로(blockFrom, blockTo)가 막혔을 때 최소 주행 시간
        int detour = dijkstraWithBlockedEdge(n, roads, start, end,
                blockFrom, blockTo);

        if (original == Integer.MAX_VALUE || detour == Integer.MAX_VALUE) {
            return -1; // 우회 경로 없는 경우
        }

        return detour - original; // 추가로 더 걸리는 시간
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] roads = {
                {0, 1, 5},   // 0↔1 : 5
                {1, 2, 3},   // 1↔2 : 3
                {0, 3, 10},  // 0↔3 : 10
                {3, 4, 1},   // 3↔4 : 1
                {2, 4, 2}    // 2↔4 : 2
        };

        int start = 0;
        int end = 4;

        // 원래 최단 경로: 0 → 1 → 2 → 4 = 5+3+2 = 10
        // 만약 (1,2) 도로가 막히면?
        int blockFrom = 1;
        int blockTo = 2;

        int extra = extraDetourTime(n, roads, start, end, blockFrom, blockTo);
        System.out.println("우회로 인한 추가 시간: " + extra);
        // 예: 원래 10, 우회하면 0→3→4 = 10+1 = 11 → 추가 1분
    }
}
