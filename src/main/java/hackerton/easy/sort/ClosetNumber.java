package hackerton.easy.sort;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class ClosetNumberTest {

    /*
     * Complete the 'closestNumbers' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static List<Integer> closestNumbers(List<Integer> arr) {
    // Write your code here
        Collections.sort(arr);
        
        int min = Integer.MAX_VALUE;
        
        for(int i=0; i<arr.size()-1; i++) {

            int minus = Math.abs(arr.get(i+1) - arr.get(i));
            if(min > minus) {
                min = minus;
            }
        }
        
        List<Integer> result = new ArrayList<>();
        for(int i=0; i<arr.size()-1; i++) {
            int minus = Math.abs(arr.get(i+1) - arr.get(i));
            if(min == minus) {
                result.add(arr.get(i));
                result.add(arr.get(i+1));
            }
        }
        return result;
        
    }

}

public class ClosetNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<Integer> result = ClosetNumberTest.closestNumbers(arr);

        bufferedWriter.write(
            result.stream()
                .map(Object::toString)
                .collect(joining(" "))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
