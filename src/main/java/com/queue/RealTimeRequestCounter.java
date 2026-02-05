package com.queue;

import java.util.ArrayDeque;
import java.util.Deque;
/*
문제
웹 서버에 요청이 실시간으로 들어온다.
최근 W초 동안 들어온 요청 개수를 빠르게 알고 싶다.
record() : “지금 시각에 요청 1건 발생”을 기록
getCount() : “현재 시각 기준, 최근 W초 동안의 요청 수”를 반환
시간은 System.currentTimeMillis() 사용,
record() / getCount() 호출은 섞여서 여러 번 호출될 수 있다
아이디어
Deque<Long>에 요청 시각(ms) 를 넣는다.
getCount() 호출 시:
덱 앞에서부터 현재시간 - W초보다 오래된 타임스탬프는 전부 제거.
남아있는 요소 수 = 최근 W초 요청 수.
record() 도 호출 시점에 같은 정리 로직을 넣어도 OK.

정수 시간(discrete time) → 칸(초)이 10개 필요 → t - (W - 1)
실시간(continuous time) → 특정 시점보다 오래되면 제거 → t - W초
* */
public class RealTimeRequestCounter {

    private final int windowSec;
    private final Deque<Long> timestamps = new ArrayDeque<>();

    public RealTimeRequestCounter(int windowSec) {
        this.windowSec = windowSec;
    }

    // 요청 1건 발생 기록
    public void record() {
        long now = System.currentTimeMillis();
        cleanup(now);
        timestamps.addLast(now);
    }

    // 최근 windowSec 초 동안 요청 수 조회
    public int getCount() {
        long now = System.currentTimeMillis();
        cleanup(now);
        return timestamps.size();
    }

    private void cleanup(long now) {
        long limit = now - windowSec * 1000L; //지금으로 부터 10초
        System.out.println(limit+"<<limit");
        while (!timestamps.isEmpty() && timestamps.peekFirst() < limit) { //최근 초 보다 오래된것 삭제
            timestamps.pollFirst();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        RealTimeRequestCounter counter = new RealTimeRequestCounter(5); // 최근 5초

        counter.record(); // t0
        Thread.sleep(1000);
        counter.record(); // t1
        Thread.sleep(1000);
        counter.record(); // t2

        System.out.println("현재(2초 지남) 최근 5초 요청 수 = " + counter.getCount()); // 3 예상

        Thread.sleep(4000); // 총 6초 경과
        System.out.println("현재(6초 지남) 최근 5초 요청 수 = " + counter.getCount()); // 2 또는 1 또는 0 (슬립시간에 따라)
    }
}
