package com.algorizm.dp.dp;
/*
문제 1: 연속 부분 배열의 최대 합 (Maximum Subarray)
문제 설명
정수 배열 nums가 있을 때,
연속된 부분 구간(subarray) 중 합이 최대가 되는 값을 구하라.
int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
여기서 최대합은:
부분 배열 [4, -1, 2, 1] → 합 = 6
정답: 6
 */
public class MaxDP {
    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int result = solve(nums);
        int result2 = solve2(nums);
        System.out.println("결과: " + result);
        System.out.println("결과: " + result2);
    }

    public static int solve(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for(int i=1; i<nums.length; i++) {
            dp[i] = Math.max(nums[i] + dp[i-1],  nums[i]);
            max = Math.max(max, dp[i]);
        }

        return max;
    }
    public static  int solve2(int[] nums){
        int current = nums[0];
        int max = nums[0];
        for( int i=1; i<nums.length; i++) {
            //이전 연속합에 이어 붙일지 (dp[i-1] + nums[i])
            //아니면 여기서 새로 시작할지 (nums[i])
            current = Math.max(nums[i] + current, nums[i]);
            max = Math.max(max, current);
        }
        return max;
    }
}
