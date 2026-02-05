package com.practice.parsing;

import java.util.*;
/*
[{"agentId":"A1","status":"DELIVERING","distance":12},
 {"agentId":"A2","status":"IDLE","distance":3},
 {"agentId":"A3","status":"IDLE","distance":7}]
status가 "IDLE"인 에이전트만 남긴다.

distance 오름차순 → agentId 오름차순으로 정렬한다.

최종적으로 **ID만 String[]**으로 반환한다.

예상 결과:

text
코드 복사
["A2", "A3"]
* */
class Agent2 {
    String id;
    String status;
    int distance;

    public Agent2(String id, String status, int distance) {
        this.id = id;
        this.status = status;
        this.distance = distance;
    }
}

public class JsonLikeParsing {

    public static String[] solution(String input) {
        // 1) 앞뒤 대괄호 제거
        input = input.trim();
        if (input.startsWith("[")) input = input.substring(1);
        if (input.endsWith("]")) input = input.substring(0, input.length() - 1);

        // 2) 객체 덩어리로 분리: "},{" 기준
        //   예: {"..."},{"..."} → {"..."}   {"..."}
        String[] parts = input.split("\\},\\s*\\{");

        List<Agent> list = new ArrayList<>();

        for (String p : parts) {
            // 양 끝 중괄호 정리
            String obj = p.trim();
            if (!obj.startsWith("{")) obj = "{" + obj;
            if (!obj.endsWith("}")) obj = obj + "}";

            String id = extractStringValue(obj, "agentId");
            String status = extractStringValue(obj, "status");
            int distance = extractIntValue(obj, "distance");

            list.add(new Agent(id, status, distance));
        }

        // 3) status == "IDLE" 필터
        list.removeIf(a -> !a.status.equals("IDLE"));

        // 4) distance → id 정렬
        list.sort((a, b) -> {
            if (a.distance != b.distance) return Integer.compare(a.distance, b.distance);
            return a.id.compareTo(b.id);
        });

        // 5) 결과 ID 배열로 반환
        String[] result = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i).id;
        }
        return result;
    }

    private static String extractStringValue(String obj, String key) {
        // 예: "agentId":"A1"
        String pattern = "\"" + key + "\":\"";
        int start = obj.indexOf(pattern);
        if (start == -1) return "";
        start += pattern.length();
        int end = obj.indexOf("\"", start);
        return obj.substring(start, end);
    }

    private static int extractIntValue(String obj, String key) {
        // 예: "distance":12
        String pattern = "\"" + key + "\":";
        int start = obj.indexOf(pattern);
        if (start == -1) return 0;
        start += pattern.length();
        int end = start;
        while (end < obj.length() && Character.isDigit(obj.charAt(end))) {
            end++;
        }
        return Integer.parseInt(obj.substring(start, end));
    }

    public static void main(String[] args) {
        String input = "[{\"agentId\":\"A1\",\"status\":\"DELIVERING\",\"distance\":12}," +
                       " {\"agentId\":\"A2\",\"status\":\"IDLE\",\"distance\":3}," +
                       " {\"agentId\":\"A3\",\"status\":\"IDLE\",\"distance\":7}]";

        String[] ans = solution(input);
        System.out.println(Arrays.toString(ans)); // [A2, A3]
    }
}
