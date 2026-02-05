package com.algorizm.bruteforce.dfs;

public class DFSBasic {

    static int[][] map = {
        {1,1,0,0},
        {0,1,0,0},
        {0,0,1,1}
    };
    
    static boolean[][] visited = new boolean[3][4];
    
    public static void main(String[] args) {
        int count = 0;
        
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 4; c++) {
                // 방문했는지 / 1인지 확인
                if (!visited[r][c] && map[r][c] == 1) {
                    dfs(r, c);   // 연결된 영역 모두 방문처리
                    count++;
                }
            }
        }
        
        System.out.println("영역 개수 = " + count); // 2
    }
    
    static void dfs(int r, int c) {
        if (r<0 || c<0 || r>=3 || c>=4) return;
        if (visited[r][c] || map[r][c] == 0) return;
        
        visited[r][c] = true;
        
        dfs(r+1, c);
        dfs(r-1, c);
        dfs(r, c+1);
        dfs(r, c-1);
    }
}
