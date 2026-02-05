package hackerton.easy.sort;


import java.io.*;
import java.util.*;

class InsertSortTest {

    /*
     * Complete the 'insertionSort2' function below.
     *
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY arr
     1 4 3 5 6 2     arr = [1, 4, 3, 5, 6, 2]
1 4 3 5 6 2 
1 3 4 5 6 2 
1 3 4 5 6 2 
1 3 4 5 6 2 
1 2 3 4 5 6 
     */

    public static void insertionSort2(int n, List<Integer> arr) {
        // Write your code here
        /*
[1, 4, 3, 5, 6, 2]
[1, 3, 4, 5, 6, 2]
[1, 3, 4, 5, 6, 2]
[1, 3, 4, 5, 6, 2]
[1, 2, 2, 2, 2, 3]
        */
        for (int i = 1; i < arr.size(); i++) { // 1~5
            int curValue = arr.get(i);
            int minIndex = i-1;
            boolean change = false;
            for (int j = i - 1; j >= 0; j--) {
                int beforeValue = arr.get(j);
                if (curValue < beforeValue) {
                    arr.set(j+1, beforeValue);
                    minIndex = j;
                    change = true;
                }
            }

            if (change) {
                arr.set(minIndex, curValue);
            }
            arr.stream().forEach(a -> System.out.print(a + " "));
            System.out.println();
        }
    }

}

public class InsertSort2 {
    public static void main(String[] args) throws IOException {


        List<Integer> a = new ArrayList<>();
        a.add(1);
        a.add(4);
        a.add(3);
        a.add(5);
        a.add(6);
        a.add(2);
        InsertSortTest.insertionSort2(6, a);

    }
}
