package hackerton.easy.find;

import java.util.Arrays;
import java.util.List;

public class PrefixSumSherlock {

    /*
     * Complete the 'balancedSums' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts INTEGER_ARRAY arr as parameter.
2
3
1 2 3
4
1 2 3 3
Sample Output 0

NO
YES
Explanation 0

For the first test case, no such index exists.
For the second test case, , therefore index  satisfies the given conditions.

Sample Input 1

3
5
1 1 4 1 1
4
2 0 0 0
4
0 0 2 0
Sample Output 1

YES
YES
YES
Explanation 1

In the first test case,  is between two subarrays summing to .
In the second case,  is between two subarrays summing to .
In the third case,  is between two subarrays summing to .
            // 0 -> -1 1
            // 1-> 0 and 2 3 4
            // 2-> 0 1 and 3 4
            // 3-> 0 1 2 and 4
            // 4-> 0 1 2 3 and aftervalue=0
     */

    public static String balancedSums(List<Integer> arr) {

        int[] leftSum = new int[arr.size()+1];
        leftSum[0] = 0;
        for(int i=0; i<arr.size(); i++){
            leftSum[i+1] = leftSum[i] + arr.get(i);
        }

        int[] rightSum = new int[arr.size()+1];
        for(int i=arr.size()-1; i>=0; i--){
            rightSum[i] = arr.get(i) + rightSum[i+1];
        }
        System.out.println(Arrays.toString(rightSum));
        for(int i=0; i<arr.size(); i++) {
            int curValue = arr.get(i);
            int beforeValue = 0;
            int afterValue = 0;

            beforeValue = leftSum[i];
            afterValue = rightSum[i+1];

            if(beforeValue == afterValue) {
                return "YES";
            }
        }
        return "NO";
    }

}
