package hackerton.easy;

import java.util.*;
import java.util.regex.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class Templates {

    /* ----------------------------------------------------
     * 1. 대문자 / 소문자 개수 세기
     * ---------------------------------------------------- */
    public static void countUpperLower(String s) {
        int upper = 0;
        int lower = 0;

        for (char c : s.toCharArray()) {
            if (Character.isUpperCase(c)) {
                upper++;
            } else if (Character.isLowerCase(c)) {
                lower++;
            }
        }

        //char upper1 = Character.toUpperCase('n');
        //boolean isLower = Character.isLowerCase(upper1);

        System.out.println("UPPER = " + upper + ", LOWER = " + lower);
    }

    /* ----------------------------------------------------
     * 2. 양수 / 음수 / 0 개수 세기
     * ---------------------------------------------------- */
    public static void countPosNegZero(int[] arr) {
        int pos = 0;
        int neg = 0;
        int zero = 0;

        for (int x : arr) {
            if (x > 0) pos++;
            else if (x < 0) neg++;
            else zero++;
        }
        //애드못함 불변임
        List<Integer> list1 = Arrays.stream(arr).boxed().toList();
        List<Integer> list = Arrays.stream(arr).boxed().collect(Collectors.toList());//콜랙트!!!
        list.add(1);
        System.out.println("POS = " + pos + ", NEG = " + neg + ", ZERO = " + zero);
    }

    /* ----------------------------------------------------
     * 3. CSV 파싱 (쉼표 기준 split + trim)
     *    예: "a, b,  c ,, d" -> ["a", "b", "c", "", "d"]
     * ---------------------------------------------------- */
    public static List<String> parseCsv(String line) {
        String[] tokens = line.split(",", -1);  // -1: 빈 문자열도 살림
        List<String> result = new ArrayList<>();
        for (String t : tokens) {
            result.add(t.trim()); // 앞뒤 공백 제거
        }
        return result;
    }

    /* ----------------------------------------------------
     * 4. 문자열에서 정수만 추출하기
     *    예: "abc-12x3 = 7" -> [-12, 3, 7]
     * ---------------------------------------------------- */
    public static List<Integer> extractIntegers(String s) {
        List<Integer> nums = new ArrayList<>();
        Pattern p = Pattern.compile("-?\\d+"); // 음수까지 포함
        Matcher m = p.matcher(s);

        while (m.find()) {
            nums.add(Integer.parseInt(m.group()));
        }
        /*
        Pattern emailPattern = Pattern.compile(
                "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
        );
        Matcher m = emailPattern.matcher("test@example.com");
        boolean ok = m.matches();
        System.out.println(ok); // true
*/
        return nums;
    }

    /* ----------------------------------------------------
     * 5. 공백 / 쉼표 섞여 있을 때 분리 공백또는 ,로 분리
     *    예: "a, b   c,d" -> ["a", "b", "c", "d"]
     *
     * ---------------------------------------------------- */
    public static String[] splitBySpaceOrComma(String s) {
        // 쉼표나 공백(한 칸 이상)을 기준으로 split
        return s.trim().split("[,\\s]+");
    }

    //정규식 없이
    public static String[] splitBySpaceOrComma2(String s) {
        String[] temp = s.split(",");   // 쉼표로만 split
        List<String> result = new ArrayList<>();

        for (String item : temp) {
            // trim 후, 공백 여러개를 기준으로 다시 나누기
            String[] parts = item.trim().split(" ");
            for (String p : parts) {
                if (!p.isEmpty()) {
                    result.add(p);
                }
            }
        }
        return result.toArray(new String[0]);
    }

    /* ----------------------------------------------------
     * 6. 날짜/시간 단순 형식 변환
     *    예: "2025-12-02 14:30" -> "02/12/2025 14:30"
     * ---------------------------------------------------- */
    public static String convertDateTimeFormat(String input) {
        // 입력: "yyyy-MM-dd HH:mm"
        DateTimeFormatter inFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        // 출력: "dd/MM/yyyy HH:mm" (원하는 형태로 수정 가능)
        DateTimeFormatter outFmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dt = LocalDateTime.parse(input, inFmt);
        return dt.format(outFmt);
    }

    /* ----------------------------------------------------
     * 7. K번째 작은 수 찾기 (정렬 + 인덱스)
     *    k는 1부터 시작한다고 가정
     * ---------------------------------------------------- */
    public static int kthSmallest(int[] arr, int k) {
        Arrays.sort(arr);           // O(n log n)
        return arr[k - 1];          // k는 1-based
    }

    /* ----------------------------------------------------
     * 8. 두 배열을 "정렬 후" 비교 (순서 무시하고 같은지)
     * ---------------------------------------------------- */
    public static boolean areArraysEqualIgnoringOrder(int[] a, int[] b) {
        if (a.length != b.length) return false;
        Arrays.sort(a);
        Arrays.sort(b);
        return Arrays.equals(a, b);
    }

    /* ----------------------------------------------------
     * 8-1. 두 배열을 "정렬 후" 비교 (순서 무시하고 같은지) List
     * ---------------------------------------------------- */
    public static boolean compareUsingList2(int[] a, int[] b) {
        List<Integer> listA = Arrays.stream(a).boxed().collect(Collectors.toList());
        List<Integer> listB = Arrays.stream(b).boxed().collect(Collectors.toList());

        Collections.sort(listA);
        Collections.sort(listB);

        return listA.equals(listB);
    }

    /* ----------------------------------------------------
     * 8-1. 두 배열을 "정렬 후" 비교 (순서 무시하고 같은지) Map
     * ---------------------------------------------------- */
    public static boolean compareUsingList3(int[] a, int[] b) {
        if (a.length != b.length) return false;
        Map<Integer, Integer> count = new HashMap();

        for (int n : a) {
            count.put(n, count.getOrDefault(n,0) + 1);
        }

        for (int n : b) {
            if(!count.containsKey(n) || count.get(n) == 0) {
                return false;
            }
            count.put(n, count.getOrDefault(n,0) -1);
        }
        return true;
    }

    /* ----------------------------------------------------
     * 9. 정렬 후 인접한 값들 간 최소 차이 구하기
     *    예: [4, 9, 1, 32, 13] -> sort -> [1,4,9,13,32]
     *    차이: 3,5,4,19 -> 최소 3
     * ---------------------------------------------------- */
    public static int minDiffAfterSort(int[] arr) {
        if (arr.length < 2) return 0; // 상황에 따라 예외 처리
        Arrays.sort(arr);             // O(n log n)

        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < arr.length; i++) {
            int diff = arr[i] - arr[i - 1];
            if (diff < minDiff) {
                minDiff = diff;
            }
        }
        return minDiff;
    }

    /* ----------------------------------------------------
     * 10. 문자열 뒤집기 (reverse)
     * ---------------------------------------------------- */
    public static String reverseString(String s) {
        // 제일 간단한 템플릿
        StringBuilder stringBuilde = new StringBuilder("zz");
        String abc = stringBuilde.append("11").reverse().toString();
        return new StringBuilder(s).reverse().toString();
    }

    /* ----------------------------------------------------
     * 11. 팔린드롬 판별 (앞뒤가 같은 문자열인지)
     * ---------------------------------------------------- */
    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
        // 또는: return s.equals(new StringBuilder(s).reverse().toString());
    }

    /* ----------------------------------------------------
     * 12. 메인에서 간단 테스트용 (선택)
     * ---------------------------------------------------- */
    public static void main(String[] args) {
        System.out.println(extractIntegers("hheee231"));

        // 1. 대문자/소문자
        countUpperLower("AbCdE123!");

        // 2. 양/음/0
        countPosNegZero(new int[]{-1, 0, 2, 3, -5});

        // 3. CSV
        System.out.println(parseCsv("a, b,  c ,, d"));

        // 4. 정수 추출
        System.out.println(extractIntegers("abc-12x3 = 7"));

        // 5. 공백/쉼표 split
        System.out.println(Arrays.toString(splitBySpaceOrComma("a, b   c,d")));

        // 6. 날짜/시간 포맷 변환
        System.out.println(convertDateTimeFormat("2025-12-02 14:30"));

        // 7. k번째
        System.out.println(kthSmallest(new int[]{7, 3, 9, 1}, 2)); // 3

        // 8. 배열 비교
        System.out.println(areArraysEqualIgnoringOrder(
                new int[]{3, 1, 2},
                new int[]{2, 3, 1}
        ));

        // 9. 최소 차이
        System.out.println(minDiffAfterSort(new int[]{4, 9, 1, 32, 13}));

        // 10. reverse
        System.out.println(reverseString("hello"));

        // 11. palindrome
        System.out.println(isPalindrome("abba"));
        System.out.println(isPalindrome("abc"));
    }
}
