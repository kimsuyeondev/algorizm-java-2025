package hackerton.sort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sort {
    public static void main(String[] args) {
        //   이건 "중복 제거 + 최신 순서 유지" 만 필요한 상황에서 쓰는 간단 버전이야.
        String[] regions = {"r","s"};

        List<String> list = new ArrayList<>();
        for (String r : regions) {
            r = r.toLowerCase();
            list.remove(r); // 이미 있으면 옛날 위치 제거
            list.add(r);    // 항상 맨 뒤 = 최신
        }

        Collections.reverse(list); // 최신 → 오래된 순
        //스트림 정렬 하고 싶을 때
        /*.stream()
                .sorted()                                   // 자연 순
                .sorted(Comparator.comparing(...))         // 커스텀 기준
        .sorted(Comparator.comparing(...).reversed())*/

        //리스트 정렬
        List<String> listSort = new ArrayList<>(List.of("seoullll", "busan", "daeguuuuuuu"));
        // 자연 순 (사전순)
        listSort.sort(String::compareTo);
        // 또는 람다로
        listSort.sort((a, b) -> a.compareTo(b));
        list.sort(Comparator.naturalOrder());     // 오름차순
        list.sort(Comparator.reverseOrder());     // 내림차순
        Collections.sort(list); // 자연 순 정렬 취향차이

        //커스텀
        //길이
        System.out.println(Arrays.toString(listSort.toArray()));
        listSort.sort(Comparator.comparing(a-> a.length()));
        listSort.sort(Comparator.comparing(String::length));
        System.out.println(Arrays.toString(listSort.toArray()));

        //맵을 정렬하고 싶을 때
        Map<String, Integer> map = new HashMap<>();
        map.put("333",1);
        map.put("446",7);
        map.put("445",7);
        map.put("444",7);
        map.put("111",3);
        map.put("222",6);

        List<Map.Entry<String,Integer>> list3 = new ArrayList<>(map.entrySet());
        list3.sort(Comparator.comparing(Map.Entry<String,Integer>::getValue));
        list3.sort(Comparator.comparing(Map.Entry::getValue));
        System.out.println(Arrays.toString(list3.toArray()));
        list3.sort(Comparator.comparing((Map.Entry<String,Integer> a) -> a.getValue()).reversed());
        System.out.println(Arrays.toString(list3.toArray()));
        list3.sort(Comparator.comparing(Map.Entry<String,Integer>::getValue));
        System.out.println(Arrays.toString(list3.toArray()));
        list3.sort(Comparator.comparing(Map.Entry<String,Integer>::getValue).reversed());
        System.out.println(Arrays.toString(list3.toArray()));
        list3.sort(Comparator.comparing((Map.Entry<String,Integer> a)-> a.getValue()).thenComparing((a, b)-> b.getKey().compareTo(a.getKey())));
        System.out.println(Arrays.toString(list3.toArray()));
        list3.sort(Comparator.comparing(Map.Entry<String,Integer>::getValue).reversed());
        System.out.println(Arrays.toString(list3.toArray()));
        list3.sort(Comparator.comparing(Map.Entry<String,Integer>::getValue)
                        .thenComparing(Comparator.comparing(Map.Entry<String,Integer>::getKey).reversed()));

        System.out.println(Arrays.toString(list3.toArray()));

        //커스텀
        List<String> dates = Arrays.asList(
                "2024-03-01",
                "2023-12-30",
                "2024-01-15"
        );
        dates.sort(Comparator
                .comparing((String d) -> LocalDate.parse(d))
                .reversed()
        );
        List<LocalDate> localDates = Arrays.asList(
            LocalDate.of(2024,03,01),
            LocalDate.of(2023,12,30)
        );
        localDates.sort(Comparator.naturalOrder());
        List<String> names = Arrays.asList("Seoul", "busan", "Daegu", "ANDONG");
        names.sort(Comparator.comparing(String::toLowerCase));
        List<Integer> nums = Arrays.asList(10, -30, 2, -7, 20);
        nums.sort(Comparator.comparing(Math::abs));

        List<Order> orders = new ArrayList<>();
        orders.sort( Comparator.comparing((Order o) -> o.date).reversed()
                .thenComparing((Order o) -> o.id));
    }
}
class Order{
    int id;
    LocalDate date;
}
