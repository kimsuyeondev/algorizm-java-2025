package com.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/*
문제 설명
간단한 텍스트 에디터가 있다.
이 에디터는 아래 2가지 명령만 지원한다.
TYPE x
현재 문자열의 맨 뒤에 문자열 x를 이어붙인다.
예: 현재 "ab" 상태에서 TYPE c → "abc"
UNDO
직전 상태로 되돌린다.
직전에 수행된 TYPE 명령 이전의 문자열로 돌아간다.
UNDO가 여러 번 연속으로 올 수도 있다.
되돌릴 상태가 없으면(처음 상태에서 UNDO 호출) → 아무 일도 하지 않는다.
초기 문자열은 항상 "" (빈 문자열)이다.
* */
public class UndoEditor {
    public String solution(String[] commands) {
        StringBuilder text = new StringBuilder();
        Deque<String> stack = new ArrayDeque<>();

        for(String command : commands) {
            if(command.startsWith("TYPE ")) {
                stack.push(text.toString());
                text.append(command.substring(5));

            }else if(command.startsWith("UNDO")) {
                if(!stack.isEmpty()) {
                    text = new StringBuilder(stack.pop());
                }
            }
        }
        return text.toString();
    }

    public static void main(String[] args) {
        UndoEditor undoEditor = new UndoEditor();
        UndoEditor solution = new UndoEditor();

        String[] commands1 = {
                "TYPE a",
                "TYPE b",
                "UNDO",
                "TYPE c"
        };
        System.out.println(solution.solution(commands1)); // ac

        String[] commands2 = {
                "TYPE hello",
                "TYPE  world",
                "UNDO",
                "UNDO",
                "TYPE hi"
        };
        System.out.println(solution.solution(commands2)); // hi

        String[] commands3 = {
                "UNDO",
                "UNDO",
                "TYPE x"
        };
        System.out.println(solution.solution(commands3)); // x

    }
}


