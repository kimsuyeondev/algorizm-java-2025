package stream.template;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListStreamTest {


    public static void main(String[] args) {

        List<String> list = List.of("bbbHello","aword","hello word", " 111 222 333");
        List<Integer> mapIntegers = list.stream().map(a-> a.length()).collect(Collectors.toList());
        mapIntegers.add(111111); // map()은 1:1로 할 수 있다
        //flatMap 핵심
        //map + 펼치기(flatten)
        //“한 요소에서 여러 요소가 나올 수 있다”
        //그래서 리스트 합치기, split 등에서 필수
        List<String> flatMapStrings = list.stream().flatMap(b-> Arrays.stream(b.trim().split(" "))).toList();

        //IntStream 외에는 Stream<Integer>는 sum이 없음 ! 참고
        int sum = mapIntegers.stream().mapToInt(a -> a).sum();

        System.out.println(Arrays.toString(mapIntegers.toArray()));
        System.out.println(Arrays.toString(flatMapStrings.toArray()));
        int sum2 = mapIntegers.stream()
                .mapToInt(a-> a)
                .sum();
        int sum3 = mapIntegers.stream()
                .mapToInt(Integer::intValue)
                .sum();
        long sum4 = mapIntegers.stream().mapToLong(Integer::longValue).sum();
        System.out.println(sum2);
        System.out.println(sum4);


    }
}
