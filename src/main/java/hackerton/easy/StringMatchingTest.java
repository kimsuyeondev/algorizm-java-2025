package hackerton.easy;

import java.io.*;
import java.util.*;

class Result2 {

    /*
     * Complete the 'anagram' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int anagram(String s) {
        if(s.length() % 2 != 0){
            return -1;
        }
        String aHalf = s.substring(0, s.length()/2);
        String bHalf = s.substring(s.length()/2, s.length());
        System.err.println(aHalf);
        System.err.println(bHalf);

        char[] aParts = aHalf.toCharArray();
        char[] bParts = bHalf.toCharArray();
        //baaacc aacccc
        Map<Character, Integer> map = new HashMap<>();
        for(char b : bParts) {
            map.put(b, map.getOrDefault(b, 0)+1);
        }
        int cnt = 0;
        for(char a : aParts) {
            System.err.println("map.get(a)" + map.get(a));
            if(map.get(a) != null && map.get(a) > 0){
                int cur = map.get(a);
                map.put(a, cur-1);
            }else {
                cnt ++;
            }
        }
        return cnt;

    }

    public static int anagram2(String s) {
        if(s.length() % 2 != 0){
            return -1;
        }
        String aHalf = s.substring(0, s.length()/2);
        String bHalf = s.substring(s.length()/2, s.length());
        System.err.println(aHalf);
        System.err.println(bHalf);

        char[] aParts = aHalf.toCharArray();
        char[] bParts = bHalf.toCharArray();
        //baaacc aacccc
        int[] alpha = new int[26];

        for(char c : aParts) {
            alpha[c - 'a']++;
        }

        for(char c : bParts) {
            alpha[c - 'a']--;
        }
        int cnt =0;
        for(int a : alpha) {
            if(a > 0) {
                cnt += a;
            }
        }

        return cnt;
    }
}

public class StringMatchingTest {
    public static void main(String[] args) throws IOException {
        String s = "aaasaass";
        int result2 = Result2.anagram(s);

    }
}
