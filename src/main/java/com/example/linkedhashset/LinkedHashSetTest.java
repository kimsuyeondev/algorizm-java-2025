package com.example.linkedhashset;

import java.util.Arrays;
import java.util.LinkedHashSet;
/*
최근 호출된 지역 목록 만들기 (중복 제거 + 순서 유지)
배차 서버에서는 사용자가 호출한 지역 목록을 저장한다.
중복된 지역은 제거하되, 처음 등장한 순서를 유지해야 한다.
지역 이름은 대소문자를 구분하지 않는다.
예: "Seoul", "SEOUL" → 같은 지역
["Seoul", "Busan", "Seoul", "Daegu", "BUSAN", "Incheon"]
["seoul", "busan", "daegu", "incheon"]
**/
public class LinkedHashSetTest {

    public static void main(String[] agrs) {
        String[] regions = {"Seoul", "Busan", "Seoul", "Daegu", "BUSAN", "Incheon"};
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(regions)));
    }
}

class Solution {
    public String[]solution(String[] regions) {
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();

        for(String region : regions) {
            linkedHashSet.add(region);
        }

        for(String str : linkedHashSet) {
            System.out.println(str);
        }
        System.out.println("===");
        for(String str : linkedHashSet) {
            System.out.println(str);
        }

        return linkedHashSet.toArray(new String[0]);
    }

    }
