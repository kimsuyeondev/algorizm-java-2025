package com.example;

/**
 * 정수 배열 A가 주어진다. (모든 원소는 0 이상)
 * <p>
 * 연속 부분 배열 A[i..j] (0 ≤ i ≤ j < N) 중에서,
 * 합이 K 이하인 구간의 개수를 구하라.
 * <p>
 * 조건
 * 1 ≤ N ≤ 200,000
 * 0 ≤ A[i] ≤ 10^9
 * 1 ≤ K ≤ 10^14
 */
public class TwoPointer_Sum_Before {

    public static void main(String[] args) {
        int[] a = {5, 10, 2, 4, 6, 2, 3};
        int k = 15;
        int left = 0;
        int count = 0;
        int sum = 0;
        for (int right = 0; right < a.length; right++) {
            sum += a[right];
            System.out.println("sum=" + sum);

            while (left <= right && sum > k) { //조건을 위반하면 (합이 K이하인 경우 가아니라 합이 커져버리면)
                sum -= a[left];
                left++;
                System.out.println("sum=" + sum);
            }
            count += right - left + 1;
        }
    }
}
