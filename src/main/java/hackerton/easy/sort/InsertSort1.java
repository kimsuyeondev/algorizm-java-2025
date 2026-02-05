package hackerton.easy.sort;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class InsertSort1Result {

    /*
     * Complete the 'insertionSort1' function below.
     *
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER_ARRAY arr
5
2 4 6 8 3

2 4 6 8 8 
2 4 6 6 8 
2 4 4 6 8 
2 3 4 6 8 
     */

    public static void insertionSort1(int n, List<Integer> arr) {
        int lastIndex = arr.size() -1;
        
        for(int i=arr.size()-1; i>0; i--){
            int compareValue = arr.get(i-1);
            int curValue = arr.get(i);
            //4-1=3
            if(curValue < compareValue) {
                arr.set(i, compareValue);
                print(arr);
                arr.set(i-1, curValue);
            }
        }
        print(arr);
               
    }
    
    public static void print(List<Integer> arr) {
        arr.stream().forEach( a-> System.out.print(a + " "));
        System.out.println();
    }

}

public class InsertSort1 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        InsertSort1Result.insertionSort1(n, arr);

        bufferedReader.close();
    }
}
