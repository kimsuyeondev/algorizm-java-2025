package hackerton.thread;

import java.util.concurrent.atomic.AtomicInteger;
/*
내부적으로 CAS(compare-and-swap)
synchronized 없이도 스레드 안전
단순 카운터 작업에서 가장 빠르고 안전함
* */
public class AtomicTemplate {

    private AtomicInteger counter = new AtomicInteger(0);

    public void increase() {
        counter.incrementAndGet(); // ++counter 원자적 연산
    }

    public int getValue() {
        return counter.get();
    }
}
