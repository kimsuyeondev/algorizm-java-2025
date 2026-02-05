package com.stack;

import java.util.ArrayDeque;
import java.util.Deque;

//LIFO
/*
스택(Stack) — 대표 패턴
패턴: “직전 상태 기억”
예:
괄호 검사
UNDO 기능
뒤로 가기
* */
public class StackTest {
    /*
    s = "()[]{}"      → true
    s = "({[]})"      → true
    s = "(]"          → false
    s = "([)]"        → false
    s = "("           → false
    s = ""            → true
    올바른 괄호 검사 패턴
    패턴은 항상 이거야:
    문자열을 왼쪽부터 한 글자씩 읽으면서:
    여는 괄호면 → push
    닫는 괄호면 →
    스택이 비었으면 false
    pop 해서 짝이 맞는지 확인
    짝이 아니면 false
    문자열 끝난 뒤:
    스택이 비어 있으면 true
    남아 있으면 false
    * */
    public static void main(String[] args) {
        String str = "()[]{}";
        Solution solution = new Solution();
        System.out.println(solution.solution(str));
    }
}

class Solution {
    public boolean solution(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) return false;
                char open = stack.pop();
                if (!isPair(open, ch)) return false;
            }
        }

        return stack.isEmpty();
    }

    private boolean isPair(char open, char close) {
        return (open == '(' && close == ')')
                || (open == '[' && close == ']')
                || (open == '{' && close == '}');
    }
}
