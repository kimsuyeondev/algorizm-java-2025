package com.algorizm.slidingwindow;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/* 콜택시 서버가 있어.
너무 많은 요청이 동시에 들어오면 서버가 버티질 못해서,
“최근 10초 동안 최대 N개까지만 처리” 하는 제한을 걸려고 함.

입력
int limit : 10초 동안 처리할 수 있는 최대 요청 수
String[] logs : "초(정수) requestId" 형식의 문자열 배열
예 "1 A", "2 B", "11 C"
시간은 초 단위 정수, 오름차순 정렬되어 들어온다고 가정

처리 규칙
각 요청은 도착한 순서대로 하나씩 본다.
어떤 요청이 시간 t에 들어올 때,
[t-9, t] 구간(최근 10초) 안에 이미 처리한 요청이 limit개 이상이면 → 이 요청은 거절(reject)
아니면 → 이 요청은 처리(accept) 하고, 처리 시각을 기록한다.
최종적으로 처리(accept)된 요청의 requestId 배열을 반환하라.

최근 N초 동안 제한할 때: 10초면 time - 9
threshold = time - (N - 1)
윈도우 범위 = [time-(N-1), time]
큐는 pollFirst, addLast
스택은 푸시 팝 픽
우선순위 큐는 오퍼, 폴
 */
public class rateLimiter {

    /**
     * @param limit 최근 10초 동안 처리 가능한 최대 요청 수
     * @param logs  "time requestId" 형식의 문자열 배열 (time은 오름차순)
     * @return 처리(accept)된 요청 id들을 순서대로 담은 배열
     */
    public String[] solution(int limit, String[] logs) {
        Deque<Integer> queue = new ArrayDeque<>();
        List<String> reqeustIds = new ArrayList<>();


        for (String log : logs) {
            String[] parts = log.split(" ");
            int time = Integer.parseInt(parts[0]);
            String requestId = parts[1];
            int threshold = time - 9;

            //큐가 비어 있지 않고, 10 >  1~10, 15 > 6~15
            while (!queue.isEmpty() && queue.peekFirst() < threshold) {
                queue.pollFirst();
            }

            if (queue.size() >= limit) {
                // reject → 아무것도 안 함
                continue;
            }
            queue.addLast(time);
            reqeustIds.add(requestId);
        }

        return reqeustIds.toArray(new String[]{});
    }
    public static void main(String[] args) {
        rateLimiter solution = new rateLimiter();

        int limit = 2;
        String[] logs = {
                "1 A",
                "2 B",
                "3 C",
                "11 D",
                "11 E",
                "12 F"
        };

        String[] result = solution.solution(limit, logs);
        for (String r : result) {
            System.out.print(r + " "); // A B D F
        }
    }

}
