package com.example.prefix;


/**
 * 핵심: 투 포인터, prefix 없이도 가능하지만 함께 쓰면 더 빠름
 * 문제 2 — 부분 배열의 합이 K 이하인 경우의 수
 * 정수 배열 A가 있을 때,
 * 연속 부분 배열 A[i..j] (i ≤ j)의 합이 K 이하인 모든 쌍 (i,j)의 개수
 * ✔ 입력
 * N ≤ 200,000
 * A[i] ≥ 0
 * K ≤ 10^12
 * ✔ 출력
 * 조건을 만족하는 구간 쌍의 개수
 */
public class PrefixSumTestWithTwoPointer {
    public static void main(String[] args) {

  /*   포인터랑 같이 굳이할거면    //L 2
        //R 4 구간
        int K = 15;
        // 6+5+2 =13
        // 17-22 =
        int[] A = {10, 1, 6, 5, 2, 1};
        int N = A.length;
        //0, 10, 11, 17, 22, 24, 25
        long[] prefix = new long[N + 1];
        prefix[0] = 0;

        for (int i = 0; i < N; i++) {
            prefix[i + 1] = prefix[i] + A[i];
        }

        int left = 0;
        int count = 0;
        for (int right = 0; right < A.length; right++) {
            long rangeSum = prefix[right + 1] - prefix[left];

            while (rangeSum > K && left <= right) {
                left++;
                rangeSum = prefix[right + 1] - prefix[left];
            }
            count += (right - left + 1);
        }

        System.out.println("count=" + count);

        */
    }

}
