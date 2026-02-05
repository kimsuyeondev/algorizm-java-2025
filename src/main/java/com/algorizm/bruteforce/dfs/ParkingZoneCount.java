package com.algorizm.bruteforce.dfs;
/*
지도에서 연결된 주차존 개수 세기 (DFS)
* */
public class ParkingZoneCount {

    public static int countZones(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];

        int count = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < m; col++) {
                if (!visited[row][col] && grid[row][col] == 1) {
                    dfs(grid, visited, row, col);
                    count++;
                }
            }
        }
        return count;
    }

    private static void dfs(int[][] grid, boolean[][] visited, int row, int col) {
        int n = grid.length;
        int m = grid[0].length;

        if (row < 0 || col < 0 || row >= n || col >= m) return;
        if (visited[row][col]) return;
        if (grid[row][col] == 0) return;

        visited[row][col] = true;

        dfs(grid, visited, row + 1, col);
        dfs(grid, visited, row - 1, col);
        dfs(grid, visited, row, col + 1);
        dfs(grid, visited, row, col - 1);
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 0, 0},
                {0, 1, 0, 1},
                {0, 0, 1, 1}
        };
        System.out.println("주차존 개수: " + countZones(grid)); // 3
    }
}
