package stream.template.inputstream;

import java.util.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class IntStreamTest {

    //a 와 b 스코어 구하기 a vs b
    public static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
        int aScore = (int) IntStream.range(0, a.size())
                .filter(i -> a.get(i) > b.get(i)).count();
        int bScore = (int) IntStream.range(0, a.size())
                .filter(i -> a.get(i) < b.get(i)).count();

        //a 와 b 재밌게 구해보기 ㅋㅋ
        int[] result = IntStream.range(0, a.size())
                .mapToObj(i -> {
                    if (a.get(i) > b.get(i)) return new int[]{1, 0};
                    else if (a.get(i) < b.get(i)) return new int[]{0, 1};
                    else return new int[]{0, 0};
                })
                .reduce(new int[2], (acc, v) -> {
                    acc[0] += v[0];
                    acc[1] += v[1];
                    return acc;
                });
        System.out.println(Arrays.toString(result));




        return List.of(aScore, bScore);// 불변 객체로set,add,remove 다 안됨 Arrays.asList(aScore,bScore); set만 됨
    }


    public static void main(String[] args) {
        Set<Character> hashSet = new HashSet<>(); // 일반
        Set<Character> treeSet = new TreeSet<>(); // 정렬된 애
        Set<Character> linkedSet = new LinkedHashSet<>(); // 입력순

        hashSet.add('3');
        hashSet.add('1');
        hashSet.add('2');

        treeSet.add('2');
        treeSet.add('1');
        treeSet.add('3');

        linkedSet.add('4');
        linkedSet.add('6');
        linkedSet.add('5');

        hashSet.stream().forEach(a -> System.out.println("hashset" + a));
        treeSet.stream().forEach(a -> System.out.println("tree" + a));
        linkedSet.stream().forEach(a -> System.out.println("link" + a));

        boolean zz = IntStream.of(1, 2, 3).anyMatch(
                i -> hashSet.contains((char) (i + '0'))
        );
        System.out.println(zz);

        boolean treeSetHasChar = treeSet.stream().anyMatch(hashSet::contains);
        boolean linkedSetHasChar = linkedSet.stream().anyMatch(treeSet::contains);
        System.out.println("treeSetHasChar : " + treeSetHasChar);
        System.out.println("linkedSetHasChar : " + linkedSetHasChar);


        compareTriplets(List.of(1, 4, 5), List.of(0, 4, 4));


        //대각선 더하기 재밌게 해보기
        List<List<Integer>> arr=  Arrays.asList(Arrays.asList(5,5,3),Arrays.asList(2,3,4),Arrays.asList(3,3,2));
        int n=arr.size();
        int[] result = IntStream.range(0, n)
                .collect(
                        () -> new int[2],
                        (acc, i) -> {
                            acc[0] += arr.get(i).get(i);
                            acc[1] += arr.get(n - 1 - i).get(i);
                        },
                        (acc1, acc2) -> {
                            acc1[0] += acc2[0];
                            acc1[1] += acc2[1];
                        }
                );

        System.out.println(Math.abs(result[0]+result[1]));
    }
}
