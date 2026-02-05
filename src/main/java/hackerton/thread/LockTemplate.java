package hackerton.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*
3) ReentrantLock — synchronized의 업그레이드 버전
특징
더 많은 기능: tryLock(), timeout, 공정성(fair) 설정
반드시 lock.unlock()를 finally에서 호출해야 함
deadlock 방지 가능

syncronized 말고 lock 써보기
* */
public class LockTemplate {

    static class Counter {
        private int count = 0;
        private final Lock lock = new ReentrantLock(); // 하나의 락 객체
        // 하나의 Lock 객체를 여러 스레드가 공유함

        public void increase() {
            lock.lock(); // 락 획득 — 다른 스레드는 여기서 대기
            try {
                count++;
            } finally {
                lock.unlock(); // 항상 락 해제
            }
        }

        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Runnable task = () -> {
            for (int i = 0; i < 10_000; i++) {
                counter.increase();
            }
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("최종 count = " + counter.getCount());
    }
}
