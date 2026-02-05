package com.practice.parsing;

import java.util.*;

class Agent {
    String id;
    String status;
    int distance;

    public Agent(String id, String status, int distance) {
        this.id = id;
        this.status = status;
        this.distance = distance;
    }
}

public class Parsing {
    public static String[] solution(String input) {

        // 1) 파싱: 세미콜론 → 에이전트
        String[] arr = input.split(";");
        List<Agent> list = new ArrayList<>();

        for (String s : arr) {
            String[] t = s.split(",");
            list.add(new Agent(t[0], t[1], Integer.parseInt(t[2])));
        }

        // 2) IDLE 필터
        list.removeIf(a -> !a.status.equals("IDLE"));

        // 3) 거리 기준 정렬
        list.sort(Comparator.comparingInt(a -> a.distance));

        // 4) agentId 배열로 반환
        return list.stream().map(a -> a.id).toArray(String[]::new);
    }

    public static void main(String[] args) {
        String input = "A1,IDLE,12;A2,DELIVERING,5;A3,IDLE,7";
        System.out.println(Arrays.toString(solution(input)));
        // 출력: [A3, A1]
    }
}
