package com.practice.bit;

public class BitTest {
        public int solution(int N) {
            int maxGap = 0;
            int currentGap = -1; // 아직 첫 1을 안 만났을 때는 -1

            while (N > 0) {
                System.out.println(Integer.toBinaryString(N));
                //비트 AND (&), OR(|), XOR(^) 전체 설명
                if ((N & 1) == 1) { // 현재 비트가 1이면
                    if (currentGap > maxGap) {
                        maxGap = currentGap; // 지금까지 gap 업데이트
                    }
                    currentGap = 0; // 새로운 갭 시작
                } else {
                    if (currentGap >= 0) {
                        currentGap++; // 1을 만난 뒤의 0만 카운트
                    }
                }
                N >>= 1; // 오른쪽으로 한 비트 이동
            }

            return maxGap;
        }

}
