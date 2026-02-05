package hackerton.easy;


import java.util.*;

public class PlusMinus {

    /*
     * Complete the 'plusMinus' function below.
     *
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static void plusMinus(List<Integer> arr) {
    // Write your code here
    
    int plus = 0;
    int minus = 0;
    int zero = 0;
        for(int i=0; i<arr.size(); i++) {
            if(arr.get(i) > 0) {
                plus++;
            }
            if(arr.get(i) < 0) {
                minus++;
            }
            if(arr.get(i) == 0) {
                zero++;
            }
        }
        
    double plusRatios = (double) plus/arr.size();
    double minusRatios = (double) minus/arr.size();
    double zeroRatios = (double) zero/arr.size();

    System.out.printf("%.6f%n", plusRatios);
    System.out.printf("%.6f%n", minusRatios);
    System.out.printf("%.6f%n", zeroRatios);
    
    
    }
}



