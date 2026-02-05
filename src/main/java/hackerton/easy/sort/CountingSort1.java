package hackerton.easy.sort;

import java.util.ArrayList;
import java.util.List;

public class CountingSort1 {

    public static List<Integer> countingSort1(List<Integer> arr) {
        int[] array = new int[100];

        for(int i=0; i<arr.size(); i++) {
            int idx = arr.get(i);
            array[idx]++;
        }
//정렬
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<100; i++) {
            int cnt = array[i];
            if(cnt > 0) {
                for(int j=0; j<cnt; j++) {
                    list.add(i);
                }
            }
        }

        return list;
    }

//몇개인지만 추출
    public static List<Integer> countingSort(List<Integer> arr) {
        int[] array = new int[100];


        for(int i=0; i<arr.size(); i++) {
            // 10 40 20
            int idx = arr.get(i);
            array[idx] = array[idx] + 1;

        }
        List<Integer> results = new ArrayList();
        for(int num : array) {
            results.add(num);
        }
        return results;

    }


}
