package stream.template;

import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.*;

public class StreamTest {

    // 간단히 쓸 User/Item 클래스
    static class User {
        private final String name;
        private final int age;
        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public String getName() { return name; }
        public int getAge() { return age; }
        @Override
        public String toString() {
            return "User{name='" + name + "', age=" + age + '}';
        }
    }

    static class Item {
        private final String type;
        private final int price;
        public Item(String type, int price) {
            this.type = type;
            this.price = price;
        }
        public String getType() { return type; }
        public int getPrice() { return price; }
        @Override
        public String toString() {
            return "Item{type='" + type + "', price=" + price + '}';
        }
    }

    public static void main(String[] args) {
        demoMap();
        demoFilter();
        demoMapToIntAndSum();
        demoFlatMap();
        demoMatch();
        demoReduce();
        demoCollect();
        demoGroupingBy();
        demoSortedAndDistinct();
        demoLimitSkipPeek();
    }

    /* ----------------------------------------------------
     * 1. map() — 요소를 다른 값으로 1:1 변환
     * ---------------------------------------------------- */
    private static void demoMap() {
        List<String> names = List.of("suyeon", "kim", "stream/template");
        List<Integer> lengths = names.stream()
                .map(String::length)
                .toList();
        System.out.println("[map] lengths = " + lengths);
    }

    /* ----------------------------------------------------
     * 2. filter() — 조건에 맞는 요소만 남기기
     * ---------------------------------------------------- */
    private static void demoFilter() {
        List<Integer> numbers = List.of(-3, -1, 0, 2, 4, 5);
        List<Integer> positives = numbers.stream()
                .filter(n -> n > 0)
                .toList();
        System.out.println("[filter] positives = " + positives);
    }

    /* ----------------------------------------------------
     * 3. mapToInt() + sum() — 숫자 계산용 IntStream
     * ---------------------------------------------------- */
    private static void demoMapToIntAndSum() {
        List<Integer> numbers = List.of(1, 2, 3, 4);
        int sumOfSquares = numbers.stream()
                .mapToInt(n -> n * n)
                .sum();
        System.out.println("[mapToInt] sum of squares = " + sumOfSquares);
    }

    /* ----------------------------------------------------
     * 4. flatMap() — 1:N 변환 + 평탄화
     *    "hello world", "foo bar" → ["hello","world","foo","bar"]
     * ---------------------------------------------------- */
    private static void demoFlatMap() {
        List<String> lines = List.of("hello world", "foo bar");
        List<String> words = lines.stream()
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .toList();
        System.out.println("[flatMap] words = " + words);
    }

    /* ----------------------------------------------------
     * 5. anyMatch / allMatch / noneMatch — 조건 검사
     * ---------------------------------------------------- */
    private static void demoMatch() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);

        boolean hasEven = numbers.stream().anyMatch(n -> n % 2 == 0);
        boolean allPositive = numbers.stream().allMatch(n -> n > 0);
        boolean noneNegative = numbers.stream().noneMatch(n -> n < 0);

        System.out.println("[match] hasEven = " + hasEven);
        System.out.println("[match] allPositive = " + allPositive);
        System.out.println("[match] noneNegative = " + noneNegative);
    }

    /* ----------------------------------------------------
     * 6. reduce() — 누적 연산(합, 최대/최소, 문자열 이어 붙이기 등)
     * ---------------------------------------------------- */
    private static void demoReduce() {
        List<Integer> numbers = List.of(3, 10, 7);

        int sum = numbers.stream()
                .reduce(0, Integer::sum); // 0부터 시작해서 더하기
        int max = numbers.stream()
                .reduce(Integer.MIN_VALUE, Integer::max);

        System.out.println("[reduce] sum = " + sum);
        System.out.println("[reduce] max = " + max);

        // 문자열 이어붙이기 예시
        List<String> parts = List.of("Hello", "Stream", "API");
        String joined = parts.stream()
                .reduce("", (a, b) -> a + (a.isEmpty() ? "" : " ") + b);
        System.out.println("[reduce] joined = " + joined);
    }

    /* ----------------------------------------------------
     * 7. collect() — List/Set/문자열 등으로 수집
     *    + joining(), toList(), toSet()
     * ---------------------------------------------------- */
    private static void demoCollect() {
        List<String> names = List.of("kim", "lee", "park", "kim");

        // List로 수집
        List<String> nameList = names.stream()
                .collect(toList());

        // Set으로 수집(중복 제거)
        Set<String> nameSet = names.stream()
                .collect(toSet());

        // 콤마로 이어 붙이기
        String csv = names.stream()
                .collect(joining(","));

        System.out.println("[collect] list = " + nameList);
        System.out.println("[collect] set = " + nameSet);
        System.out.println("[collect] csv = " + csv);
    }

    /* ----------------------------------------------------
     * 8. groupingBy() — SQL GROUP BY 느낌
     *    타입별 개수 세기, 합계 내기 등
     * ---------------------------------------------------- */
    private static void demoGroupingBy() {
        List<Item> items = List.of(
                new Item("food", 1000),
                new Item("food", 1500),
                new Item("book", 8000),
                new Item("book", 12000),
                new Item("etc", 3000)
        );

        // 타입별 개수
        Map<String, Long> countByType = items.stream()
                .collect(groupingBy(Item::getType, counting()));

        // 타입별 가격 합계
        Map<String, Integer> sumByType = items.stream()
                .collect(groupingBy(
                        Item::getType,
                        summingInt(Item::getPrice)
                ));

        System.out.println("[groupingBy] countByType = " + countByType);
        System.out.println("[groupingBy] sumByType   = " + sumByType);
    }

    /* ----------------------------------------------------
     * 9. sorted() + distinct() — 정렬 & 중복 제거
     * ---------------------------------------------------- */
    private static void demoSortedAndDistinct() {
        List<Integer> nums = List.of(5, 1, 3, 3, 2, 5, 4);

        List<Integer> sorted = nums.stream()
                .sorted()
                .toList();

        List<Integer> distinctSorted = nums.stream()
                .distinct()
                .sorted()
                .toList();

        System.out.println("[sorted]        " + sorted);
        System.out.println("[distinct+sort] " + distinctSorted);

        // 객체 정렬 예시
        List<User> users = List.of(
                new User("kim", 30),
                new User("lee", 20),
                new User("park", 25)
        );
        List<User> sortedByAge = users.stream()
                .sorted(Comparator.comparing(User::getAge))
                .toList();
        System.out.println("[sorted users]  " + sortedByAge);
    }

    /* ----------------------------------------------------
     * 10. limit(), skip(), peek() — 슬라이싱 & 디버깅
     * ---------------------------------------------------- */
    private static void demoLimitSkipPeek() {
        List<Integer> nums = IntStream.rangeClosed(1, 20)
                .boxed()
                .toList();

        List<Integer> first5 = nums.stream()
                .limit(5)
                .toList();

        List<Integer> skip5Take5 = nums.stream()
                .skip(5)
                .limit(5)
                .toList();

        System.out.println("[limit] first5 = " + first5);
        System.out.println("[skip+limit]    = " + skip5Take5);

        // peek() : 중간 과정 디버깅용
        int sum = nums.stream()
                .peek(n -> System.out.println("peek: " + n))
                .mapToInt(n -> n)
                .sum();
        System.out.println("[peek] sum = " + sum);
    }
}
