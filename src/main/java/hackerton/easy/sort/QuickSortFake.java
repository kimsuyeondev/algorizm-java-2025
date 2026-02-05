package hackerton.easy.sort;

import java.util.ArrayList;
import java.util.List;
public class QuickSortFake {
    public static void main(String[] args) {
/*
7
5 8 3 2 7 9 1
3 2 1
left-> 1
5
8 7 9
      left->7
*/
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(8);
        list.add(3);
        list.add(2);
        list.add(7);
        list.add(9);
        list.add(1);
        List<Integer> results = getHalfResult(list);
    }

    public static void print(List<Integer> results) {
        results.stream().forEach(a -> {
            System.out.print(a + " ");
        });

        System.out.println();
    }

    public static List<Integer> getHalfResult(List<Integer> list) {
        if(list.size() <= 1) {
            return list;
        }

        List<Integer> lefts = new ArrayList<>();
        List<Integer> rights = new ArrayList<>();
        int p = list.get(0);
        for(int i=0; i<list.size(); i++) {
            int value = list.get(i);
            if(p > value) {
                lefts.add(value);
            }
            if(p < value) {
                rights.add(value);
            }
        }

        List<Integer> sortedLeft = getHalfResult(lefts);
        List<Integer> sortedRight = getHalfResult(rights);
        List<Integer> results = new ArrayList();
        results.addAll(sortedLeft);
        results.add(p);
        results.addAll(sortedRight);

        print(results);


        return results;
    }

}

