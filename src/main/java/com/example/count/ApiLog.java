package com.example.count;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * API 로그에서 사용자별 성공 요청 수 세기
 * */
public class ApiLog {

    public static void main(String[] args) {

        String[] logs = {
                "2024-11-23T10:15:30 user01 CREATE_ORDER SUCCESS",
                "2024-11-23T10:15:31 user02 GET_DRIVER FAIL",
                "2024-11-23T10:15:32 user01 GET_DRIVER SUCCESS",
                "2024-11-23T10:16:10 user03 CREATE_ORDER SUCCESS",
                "2024-11-23T10:16:11 user02 CREATE_ORDER SUCCESS",
                "2024-11-23T10:16:12 user02 CANCEL_ORDER SUCCESS",
                "2024-11-23T10:16:12 user04 CANCEL_ORDER FAIL",
        };
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.solution(logs)));
    }
}

class Solution {
    public String[] solution(String[] logs) {

        Map<String, Integer> countMap = new HashMap<>();


        for (String log : logs) {
            String[] parts = log.split(" ");
            String userId = parts[1];
            String status = parts[3];
            System.out.println(userId);
            System.out.println(status);
            System.out.println(countMap.get(userId));
            if ("SUCCESS".equals(status)) {
                countMap.put(userId, countMap.getOrDefault(userId, 0) + 1);
            }
        }

        List<Map.Entry<String,Integer>> list = new ArrayList<>(countMap.entrySet());

/*
for (Map.Entry<String, Integer> e : map.entrySet()) {
    System.out.println(e.getKey() + " = " + e.getValue());
}
* */

        list.sort(Comparator.comparing((Map.Entry<String, Integer> map)-> map.getValue()).reversed()
                .thenComparing((Map.Entry<String, Integer> entry) -> entry.getKey()));


    /*    list.sort((a,b) -> {
            int cmp = b.getValue() - a.getValue(); //내림차순
            if(cmp != 0) {
                return cmp;
            }
            return a.getKey().compareTo(b.getKey()); //오름차순
        });*/
        List<String> result = new ArrayList<>();
        for(Map.Entry<String, Integer> map : list) {
            result.add(map.getKey());
        }

        String[] resultArray = result.toArray(new String[]{});

        return resultArray;
    }
    public String[] solution2(String[] logs) {
        List<ApiLogDto> apiLogs = new ArrayList<>();
        for (String log : logs) {
            String[] parts = log.split(" ");
            String userId = parts[1];
            String status = parts[3];
            apiLogs.add(new ApiLogDto(userId, status));
        }

        Map<String, Long> countMap = apiLogs.stream().filter(a -> "SUCCESS".equals(a.status))
                .collect(Collectors.groupingBy((ApiLogDto a)-> a.userId, Collectors.counting()));
        //리스트 -> 맵!!!

        //맵 -> 리스트!!!
        List<Map.Entry<String,Long>> list = new ArrayList<>(countMap.entrySet());
        list.sort(Comparator.comparing((Map.Entry<String, Long> map)-> map.getValue()).reversed()
                .thenComparing((Map.Entry<String, Long> entry) -> entry.getKey()));

        /* list.sort((a,b) -> {
            int cmp = b.getValue() - a.getValue(); //내림차순
            if(cmp != 0) {
                return cmp;
            }
            return a.getKey().compareTo(b.getKey()); //오름차순
        });*/
        List<String> result = new ArrayList<>();
        for(Map.Entry<String, Long> map : list) {
            result.add(map.getKey());
        }

        String[] resultArray = result.toArray(new String[]{});

        return resultArray;
    }

}

class ApiLogDto {
    public String userId;
    public String status;

    public ApiLogDto(String userId , String status) {
        this.userId = userId;
        this.status = status;
    }
}

