package com.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/*
문제 설명
VISIT url
새 페이지 방문
현재 페이지가 있으면 back 스택에 push
forward 스택은 비운다
BACK
back 스택이 비어 있으면 무시
비어 있지 않으면:
현재 페이지를 forward 스택에 push
back 에서 pop 한 페이지를 현재 페이지로
FORWARD
forward 스택이 비어 있으면 무시
비어 있지 않으면:
현재 페이지를 back 스택에 push
forward 에서 pop 한 페이지를 현재 페이지로
* */
public class BrowserHistory {
    public String solution(String[] commands) {
        String cur = null;

        Deque<String> forwardStack = new ArrayDeque<>();
        Deque<String> backStack = new ArrayDeque<>();

        for(String command : commands) {
            if(command.startsWith("VISIT ")){
                if(cur != null) {
                    backStack.push(cur);
                }
                forwardStack.clear();
                cur = command.substring(5);

            }else if("BACK".equals(command)) {
                if(!backStack.isEmpty()){
                    if(cur != null) {
                        forwardStack.push(cur);
                    }
                    cur = backStack.pop();
                }

            }else if("FORWARD".equals(command)){
                if(!forwardStack.isEmpty()) {
                    backStack.push(cur);
                    cur = forwardStack.pop();
                }

            }
        }
        return cur;
    }

    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory();
        String[] commands1 = {
                "VISIT A",
                "VISIT B",
                "BACK",
                "VISIT C",
                "BACK",
                "FORWARD"
        };
        System.out.println(browser.solution(commands1)); // C

        String[] commands2 = {
                "BACK",
                "VISIT google",
                "VISIT naver",
                "BACK",
                "BACK",
                "FORWARD"
        };
        System.out.println(browser.solution(commands2)); // naver 가 맞음

        String[] commands3 = {
                "VISIT A",
                "VISIT B",
                "VISIT C",
                "BACK",
                "BACK",
                "BACK"
        };
        System.out.println(browser.solution(commands3)); // A

        String[] commands4 = {
                "VISIT A",
                "BACK",
                "FORWARD",
                "FORWARD"
        };
        System.out.println(browser.solution(commands4)); // A

    }
}


