package hackerton.easy.xor;

import java.io.*;

class MaximizingXorResult {

    /*
     * Complete the 'maximizingXor' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER l
     *  2. INTEGER r
     */

    public static int maximizingXor(int l, int r) {
        int max = 0;

        for (int i = l; i <= r; i++) {
            for (int j = i; j <= r; j++) {  // i ≤ j 로 중복 방지
                int value = i ^ j;
                if (value > max) {
                    max = value;
                }
            }
        }
        return max;
    }


}

public class MaximizingXor {
    public static void main(String[] args) throws IOException {
    int l= 10, r=15;
        int result = MaximizingXorResult.maximizingXor(l, r);
        System.out.println(result);
    }
}
