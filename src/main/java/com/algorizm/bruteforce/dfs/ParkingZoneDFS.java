package com.algorizm.bruteforce.dfs;

public class ParkingZoneDFS {

    public int solution(int[][] A) {
        if (A == null || A.length == 0) return 0;

        int n = A.length;
        int m = A[0].length;
        boolean[][] visited = new boolean[n][m];

        int count = 0;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                // 아직 방문 안 했고, 주차 구역(1)이면 → 새로운 Zone 시작
                if (!visited[r][c] && A[r][c] == 1) {
                    dfs(A, visited, r, c);
                    count++;
                }
            }
        }

        return count;
    }

    private void dfs(int[][] A, boolean[][] visited, int r, int c) {
        int n = A.length;
        int m = A[0].length;

        // 1) 범위 밖이면 종료
        if (r < 0 || c < 0 || r >= n || c >= m) return;

        // 2) 이미 방문했거나, 0(주차구역 아님)이면 종료
        if (visited[r][c] || A[r][c] == 0) return;

        // 3) 방문 처리
        visited[r][c] = true;

        // 4) 상하좌우 DFS
        dfs(A, visited, r + 1, c); // 아래
        dfs(A, visited, r - 1, c); // 위
        dfs(A, visited, r, c + 1); // 오른쪽
        dfs(A, visited, r, c - 1); // 왼쪽
    }
/*
Stack<int[]> stack = new Stack<>();
stack.push(new int[]{r, c});

while (!stack.isEmpty()) {
    int[] cur = stack.pop();
    int x = cur[0];
    int y = cur[1];

    if (visited[x][y]) continue;
    visited[x][y] = true;

    stack.push(new int[]{x+1, y});
    stack.push(new int[]{x-1, y});
    stack.push(new int[]{x, y+1});
    stack.push(new int[]{x, y-1});
}

* */
    // 간단 테스트용 main
    public static void main(String[] args) {
        ParkingZoneDFS p = new ParkingZoneDFS();

        int[][] A = {
                {1, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 1}
        };

        System.out.println(p.solution(A)); // 2 기대
    }
}
