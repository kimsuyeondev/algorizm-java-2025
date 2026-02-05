package hackerton.easy.game;

public class Nimble {
    String nimble(int[] s) {
        int nimSum = 0;

        for (int i = 0; i < s.length; i++) {
            if (s[i] % 2 == 1) {   // 홀수 개 동전인 칸만 고려
                nimSum ^= i;
            }
        }

        return nimSum == 0 ? "Second" : "First";
    }

}
