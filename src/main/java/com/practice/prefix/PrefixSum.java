package com.practice.prefix;

public class PrefixSum {
    public static void main(String[] args) {
        //L 2
        //R 4 구간
        int L=2;
        int R=4;
        // 6+5+2 =13
        // 17-22 =
        int[] A = {10,1,6,5,2,1};
        int N = A.length;
        //0, 10, 11, 17, 22, 24, 25
        long[] prefix = new long[N + 1];
        prefix[0] = 0;

        for (int i = 0; i < N; i++) {
            prefix[i + 1] = prefix[i] + A[i];
        }

        long rangeSum = prefix[R + 1] - prefix[L];
    }
}
