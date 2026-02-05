package com.algorizm.bruteforce.bfs;

import java.util.LinkedList;
import java.util.Queue;

/*
실전 BFS 문제 — “미로에서 단 거리최 찾기”
문제
2D 격자(지도)가 있고,
0은 장애물, 1은 이동 가능한 곳일 때,
(0,0) → (N-1,M-1)까지
최소 몇 번 이동해야 가는지 BFS로 구하라.
상하좌우 이동 가능.
예시 입력
1 1 1r
1 0 1
1 1 1
출발 (0,0)
도착 (2,2)
정답 = 4
가능한 최단 경로:
(0,0) → (1,0) → (2,0) → (2,1) → (2,2)
총 이동 4번.
큐를쓰는이유 : stack을 쓰면 한방향으로 계속 깊이 들어갈거라서
* */
public class BFSShortestPath {

    public int bfs(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        // 방문 배열
        boolean[][] visited = new boolean[n][m];
        // 시작점이 막혀 있으면 바로 실패
        if (grid[0][0] == 0) return -1;

        // 이동 방향: 상, 하, 좌, 우
        int[] upDwon = {-1, 1, 0, 0};
        int[] leftRight = {0, 0, -1, 1};
        //grid[row][col] ==> Row : 세로 / col 가로
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0}); //좌표 0,0 이랑 거리
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] currentPosition = queue.poll();
            int row = currentPosition[0];
            int col = currentPosition[1];

            int distance = currentPosition[2];

            if (row == n - 1 && col == m - 1) {
                return distance;
            }

            for (int i = 0; i < 4; i++) {//4방향
                int newRow = row + upDwon[i];
                int newCol = col + leftRight[i];

                if (newRow < 0 || newCol < 0 || newRow >= n || newCol >= m) {
                    continue;
                }

                if (visited[newRow][newCol]) {
                    continue;
                }

                if (grid[newRow][newCol] == 0) {
                    continue;
                }

                visited[newRow][newCol] = true;
                queue.offer(new int[]{newRow, newCol, distance+1});

            }
        }

        return -1; // 도달 불가
    }

    public static void main(String[] args) {
        BFSShortestPath solver = new BFSShortestPath();

        int[][] grid = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };

        int result = solver.bfs(grid);
        System.out.println("최소 이동 횟수: " + result);  // 4
    }
}
