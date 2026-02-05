package hackerton.easy.find;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
Sample Input

STDIN       Function
-----       --------
2           t = 2
4           k = 4
5           cost[] size n = 5
1 4 5 3 2   cost = [1, 4, 5, 3, 2]
4           k = 4
4           cost[] size n = 4
2 2 4 3     cost=[2, 2,4, 3]
Sample Output

1 4
1 2
* */
public class TwoSum {

    public static List<Integer> icecreamParlor(int m, List<Integer> arr) {
        // Write your code here
        /**/
        int size = arr.size();
        List<Integer> result = new ArrayList<>();
        //curvalue, index i
        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<size; i++) {
            int need = m - arr.get(i);

            if(map.containsKey(need)){
                result.add(map.get(need)+1);
                result.add(i+1);
                return result;
            }

            if(!map.containsKey(need)) {
                map.put(arr.get(i),i);
            }

        }

        return result;
    }
}
