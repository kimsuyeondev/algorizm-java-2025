package com.algorizm.bruteforce.bfs;

import java.util.*;
/*
문제: Dijkstra + 우회 비용 포함 경로 탐색
문제 설명
도시들의 그래프가 있다.
각 도로는 4개의 정보로 주어진다:
from : 출발 도시
to : 도착 도시
baseTime : 기본 이동 시간
detourCost : 공사·우회로 등으로 인해 추가되는 시간
이동 시간은:
totalWeight = baseTime + detourCost
형태로 계산한다.
목표
시작 도시 start에서 도착 도시 end까지
이동 시간의 최솟값을 구하라.
도달할 수 없으면 -1을 출력하라.
입력
n = 도시 개수 (1~10^5)
roads = [
   [from, to, baseTime, detourCost],
   ...
]
start, end
도로는 “양방향”으로 가정.
출력
start → end 최단 이동 시간
단, 도달할 수 없으면 -1
* */
public class DijkstraDetour {

    public static int shortestPath(int n, int[][] roads, int start, int end) {

        // graph[u] = (v, weight) 리스트
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

        // roads: {from, to, baseTime, detour}
        for (int[] r : roads) {
            int from = r[0], to = r[1], base = r[2], detour = r[3];
            int w = base + detour;
            graph.get(from).add(new int[]{to, w});
            graph.get(to).add(new int[]{from, w}); // 양방향이면 추가
        }

        // Dijkstra
        int INF = Integer.MAX_VALUE;
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.add(new int[]{0, start});   // {dist, node}

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curDist = cur[0];
            int node = cur[1];

            if (curDist > dist[node]) continue;
            if (node == end) break;

            for (int[] nxt : graph.get(node)) {
                int next = nxt[0];
                int weight = nxt[1];
                int nd = curDist + weight;

                if (nd < dist[next]) {
                    dist[next] = nd;
                    pq.add(new int[]{nd, next});
                }
            }
        }

        return dist[end] == INF ? -1 : dist[end];
    }

    public static void main(String[] args) {

        int n = 3;
        int[][] roads = {
                {1, 2, 5, 0},   // 1-2, weight=5
                {2, 3, 2, 5},   // 2-3, weight=7 (우회/공사)
                {1, 3, 15, 0}   // 1-3 직통, weight=15
        };

        int result = shortestPath(n, roads, 1, 3);
        System.out.println("1→3 최소 시간 = " + result);  // 12
    }
}
