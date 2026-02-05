package hackerton.easy.find;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Result {
    /*
     * Complete the 'missingNumbers' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY arr
     *  2. INTEGER_ARRAY brr
     * missing-numbers
     * Sample Input

10
203 204 205 206 207 208 203 204 205 206
13
203 204 204 205 206 207 205 208 203 206 205 206 204
Sample Output

204 205 206
     */
    public static List<Integer> missingNumbers(List<Integer> arr, List<Integer> brr) {
        int j=0;
        
        Map<Integer,Integer> aMap = new HashMap<>();
        for(int number : arr ) {
            aMap.put(number, aMap.getOrDefault(number, 0) + 1);
        }

        Map<Integer,Integer> bMap = new HashMap<>();
        for(int number : brr ) {
            bMap.put(number, bMap.getOrDefault(number,0)+1);
        } 
        
        Set<Integer> resultSet = new HashSet<>();

        for(int i=0; i<brr.size(); i++) {
            int value = brr.get(i);
            if(aMap.get(value) == null) {
                resultSet.add(value);
            }else{
               int diff = aMap.get(value) - bMap.get(value);
               
               if(diff !=0) {
                    resultSet.add(value);
               }
            }
        }
        /*
        이게 더 좋음..
        List<Integer> list = new ArrayList<>();
        for (int key : bMap.keySet()) {
            if (bMap.get(key) > aMap.getOrDefault(key,0)) {
                result.add(key);
           }
        }
        * */
        List<Integer> result = new ArrayList<>(resultSet);
        Collections.sort(result);
        return result;
    }

}
