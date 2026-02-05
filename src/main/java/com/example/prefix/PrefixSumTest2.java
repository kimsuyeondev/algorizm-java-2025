package com.example.prefix;
/**
 * 정수 배열 A가 주어진다.
 * 길이는 최대 200,000이다.
 * Q개의 쿼리(L[i], R[i])가 주어진다.
 * 각 쿼리마다 다음 값을 구하라:
 * A[L] ~ A[R] 구간합이 K 이상인지?
 * → 맞으면 1, 아니면 0
 * ✔ 입력
 * A 길이 N (1 ≤ N ≤ 200,000)
 * A[i] (0 ≤ A[i] ≤ 1,000)
 * Q (1 ≤ Q ≤ 200,000)
 * L[i], R[i]: 0-based
 * K는 하나의 정수
 * ✔ 출력
 * 길이 Q의 배열
 * 각 쿼리에서 구간합 ≥ K → 1
 * 아니면 0
 * */
public class PrefixSumTest2 {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5};
        long[] prefix = new long[a.length+1];
        prefix[0] = 0;
        int K = 14;
        int[] L = {1,1};
        int[] R = {2,4};

        for (int i = 0; i < a.length; i++) {
            prefix[i + 1] = prefix[i] + a[i];
        }

        int[] answer = new int[L.length];

        for (int i = 0; i < R.length; i++) {
            int l = L[i];
            int r = R[i];
            long sum = prefix[r + 1] - prefix[l];
            answer[i] = (sum >= K ? 1 : 0);
            System.out.println(answer[i]);

        }

     }

}
