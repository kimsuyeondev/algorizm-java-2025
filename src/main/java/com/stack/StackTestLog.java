package com.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/*
문제 2. 로그 기반 괄호 검사 (실무형 변형)
이번에는 진짜 “로그 시뮬레이션 + 괄호 패턴” 버전이야.
문제 설명
어떤 서비스에서 작업의 시작과 종료를 로그로 남긴다.
각 줄은 다음 형식이다:
"START <taskName>"
"END <taskName>"
규칙:
START task 가 나오면 해당 task가 시작된다.
END task 가 나오면, 마지막으로 시작됐지만 아직 끝나지 않은 작업이 task와 이름이 같아야 한다.
모든 로그를 처리한 후, 모든 작업이 정상적으로 종료되어야 한다. (중간에 남아 있으면 안 됨)
다음 함수를 구현하라:
* */
public class StackTestLog {
    public static void main(String[] args) {
        String[] str = {"START <taskName>", "END <taskName>"};
        String[] logs = {
                "START A",
                "START B",
                "END B",
                "START C",
                "END C",
                "END A"};
        Solution2 solution2 = new Solution2();

        System.out.println(solution2.solution(logs));
        String[] logs2 = {
                "START A",
                "START B",
                "END B"
        };
        System.out.println(solution2.solution(logs2));
        String[] logs3 = {
                "START A",
                "START B",
                "END B"
        };
        System.out.println(solution2.solution(logs3));

    }
}

class Solution2 {
    public boolean solution(String[] logs) {
        Deque<String> stack = new ArrayDeque<>();
        for (String log : logs) {
            String[] parts = log.split(" ");

            if ("START".equals(parts[0])) {
                stack.push(parts[1]);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                String pop = stack.pop();
                if (!parts[1].equals(pop)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();


    }
}
