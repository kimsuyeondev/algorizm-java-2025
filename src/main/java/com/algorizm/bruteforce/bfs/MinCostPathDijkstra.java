package com.algorizm.bruteforce.bfs;
import java.util.Arrays;
import java.util.PriorityQueue;

public class MinCostPathDijkstra {

    public int solve(int[][] cost) {

        int rowCount = cost.length;
        int colCount = cost[0].length;

        // dist[row][col] = (0,0) → (row,col) 최소 비용
        int[][] dist = new int[rowCount][colCount];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // 방향 (상, 하, 좌, 우)
        int[] upDown = {-1, 1, 0, 0};
        int[] leftRight = {0, 0, -1, 1};

        // PQ 안에는 {row, col, totalCost} 저장
        PriorityQueue<int[]> priorityQueue =
                new PriorityQueue<>((a, b) -> a[2] - b[2]);
        // 시작점
        dist[0][0] = cost[0][0];
        priorityQueue.offer(new int[]{0, 0, cost[0][0]});

        while (!priorityQueue.isEmpty()) {

            int[] cur = priorityQueue.poll();
            int row = cur[0];
            int col = cur[1];
            int totalCost = cur[2];

            // 이미 더 좋은 경로로 방문한 적 있으면 스킵
            if (totalCost > dist[row][col]) continue;

            // 도착
            if (row == rowCount - 1 && col == colCount - 1) {
                return totalCost;
            }

            // 4방향 탐색
            for (int i = 0; i < 4; i++) {

                int newRow = row + upDown[i];
                int newCol = col + leftRight[i];

                // 범위 체크
                if (newRow < 0 || newCol < 0 || newRow >= rowCount || newCol >= colCount)
                    continue;

                int newCost = totalCost + cost[newRow][newCol];

                // 더 좋은 비용 발견 → dist 갱신하고 PQ에 넣음
                if (newCost < dist[newRow][newCol]) {
                    dist[newRow][newCol] = newCost;
                    priorityQueue.offer(new int[]{newRow, newCol, newCost});
                }
            }
        }

        return -1; // 불가능 (거의 없음)
    }

    public static void main(String[] args) {
        MinCostPathDijkstra solver = new MinCostPathDijkstra();

        int[][] cost = {
                {1, 3, 1},
                {2, 5, 1},
                {4, 2, 1}
        };

        int result = solver.solve(cost);
        System.out.println("최소 비용: " + result);  // 7
    }
}

