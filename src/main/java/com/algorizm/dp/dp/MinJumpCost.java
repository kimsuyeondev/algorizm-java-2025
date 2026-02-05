package com.algorizm.dp.dp;

public class MinJumpCost {
/*
cost[i] : i번째 칸에 착지할 때 드는 비용
한 번에 1칸 또는 2칸 점프 가능
0번에서 시작해 마지막 칸까지 최소 비용
* */
    public static int minCost(int[] cost) {
        int n = cost.length;
        if (n == 1) return cost[0];

        int[] dp = new int[n];
        dp[0] = cost[0];
        dp[1] = Math.min(cost[0] + cost[1], cost[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] cost = {3, 2, 5, 1}; // 예시
        System.out.println("점프 최소 비용: " + minCost(cost)); // 6
    }
}
