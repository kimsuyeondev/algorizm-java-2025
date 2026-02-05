package com.algorizm.dp.dp;

/*
계단 오르기 최소 비용 문제
- cost[i] = i번째 계단을 밟을 때 드는 비용
- 한 번에 1계단 혹은 2계단까지 올라갈 수 있음
- 마지막 계단에 도착했을 때의 최소 비용을 구하라.
예)
cost = [3, 2, 5, 1]
*/

public class MinCostStairDP {

    public static void main(String[] args) {
        int[] cost = {3, 2, 5, 1};

        int result = solve(cost);
        System.out.println("최소 비용: " + result);
    }

    public static int solve(int[] cost) {
        int length = cost.length;
        if (length == 1) {
            return cost[0];
        }
        int[] dp = new int[length];

        dp[0] = cost[0];
        dp[1] = Math.min(cost[0] + cost[1], cost[1]);
        for (int i = 2; i < length; i++) {
            dp[i] = Math.min(dp[i-1], dp[i-2]) + cost[i];
        }
        return dp[length - 1]; //“마지막 칸에 도착했을 때 최소 비용”
        // 1. 길이 n 구하기
        // 2. n이 1일 때, 그냥 cost[0] 리턴
        // 3. dp 배열 만들기 (int[] dp = new int[n])
        // 4. dp[0], dp[1] 초기값 설정
        //    - dp[0] = cost[0]
        //    - dp[1] = Math.min(cost[0] + cost[1], cost[1])
        // 5. i = 2 ~ n-1 까지
        //    - dp[i] = Math.min(dp[i-1], dp[i-2]) + cost[i]
        // 6. 마지막 dp[n-1] 리턴
    }
}
