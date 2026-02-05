package com.example.keyvaluefiltering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
문제: key=value 로그에서 조건에 맞는 userId 필터링
 배차/회원 시스템에서 다음과 같은 형태의 로그가 쌓인다고 하자.
 각 로그는 ; 로 구분된 key=value 쌍으로 이루어져 있다.
 예시:
 "userId=U1;status=ACTIVE;age=25"
 "userId=U2;status=BLOCKED;age=31"
 "userId=U3;status=ACTIVE;age=19"
 "userId=U4;status=ACTIVE;age=40"
조건
 아래 조건을 만족하는 userId만 골라서,
 userId 오름차순(사전순) 으로 정렬해 배열로 반환하라.
 status 가 "ACTIVE" 인 사용자
 age 가 정수로 25 이상인 사용자
 */
public class KeyValueFiltering {
    // 좋은 예: 상수화(캐싱)
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public boolean isValid(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
    public static void main(String[] args) {

        Solution solution = new Solution();
        String[] lines = { "userId=U1;status=ACTIVE;age=25",
                "userId=U2;status=BLOCKED;age=31",
                "userId=U3;status=ACTIVE;age=19",
                "userId=U4;status=ACTIVE;age=40"};

        System.out.println(Arrays.toString(solution.solution(lines)));
    }
}
class User {
    String userId;
    String status;
    int age;

    public User(String userId, String status, String age) {
        this.userId = userId;
        this.status = status;
        this.age = Integer.parseInt(age);
    }
}
class Solution {
/*    아래 조건을 만족하는 userId만 골라서,
    userId 오름차순(사전순) 으로 정렬해 배열로 반환하라.
    status 가 "ACTIVE" 인 사용자
    age 가 정수로 25 이상인 사용자*/
    public String[] solution(String[] lines) {
        List<String> results = new ArrayList<>();


        for(String line : lines) {
            String userId = null;
            String status = null;
            Integer age = null;

            String[] parts = line.split(";");
            for(String part :parts) {
                String[] kv = part.split("=");
                String key = kv[0];
                String value = kv[1];

                switch (key) {
                    case "userId" :
                        userId = value;
                        break;
                    case "status" :
                        status = value;
                        break;
                    case "age":
                        age = Integer.parseInt(value);
                        break;
                }
                if(userId != null && status != null && age != null) {
                    if("ACTIVE".equals(status) && age >= 25){
                        results.add(userId);
                    }
                }

            }
        }
        //Collections.sort(results);
        results.sort((a,b)-> a.compareTo(b));
        return results.toArray(new String[results.size()]);
    }
}
