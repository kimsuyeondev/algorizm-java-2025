package com.algorizm.bruteforce;

public class bitmask {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        int n = arr.length;
        System.out.println((1 << 0));
        for (int mask = 0; mask < (1 << n); mask++) {  // 0 ~ 7
            System.out.print("subset = { ");
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) { // i번째 비트가 1이면
                    System.out.print(arr[i] + " ");
                }
            }
            System.out.println("}");
        }
    }
}
