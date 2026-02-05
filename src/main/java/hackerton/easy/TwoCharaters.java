package hackerton.easy;
import java.io.*;
import java.util.*;

class Result {

    /*
     * Complete the 'alternate' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int alternate(String s) {
        // Write your code here
        char[] array = s.toCharArray();
        Set<Character> set = new HashSet();
        for(char c : array) {
            set.add(c);
        }
        List<Character> list = new ArrayList<>(set);

        int cnt =0;
        int idx = 0;
        int maxCnt =0;
        for(int i = 0; i<list.size(); i++) {
            idx = 0;
            //set b e a f
            //01 02 03 12 13 23
            //be ba bf ea ef af 3+2+1
            // b e f a d
            // be bf ba bd ef ea ed fa fd ad
            //abc
            for(int j=i; j<list.size()-1; j++) {
                char c1 = list.get(i);     //a
                char c2 = list.get(j+1);   //b
                cnt = 0;
                char pre = ' ';
                for(char c : array) {
                    if (c == c1 || c == c2) {
                        if(pre == c) {
                            cnt = 0;
                            break;
                        }
                        cnt++;  //2
                        pre = c; //
                    }
                }
                if(cnt>1 ) {
                    maxCnt = Math.max(cnt,maxCnt);
                }
            }
        }
        return maxCnt;
    }

}

public class TwoCharaters {
    public static void main(String[] args) throws IOException {
        System.out.println(Result.alternate("abc"));
    }
}

