package hackerton.thread;
/*
6) volatile — “즉시 최신 값으로 읽게 하기”
언제 쓰나?
스레드 종료 신호를 보낼 때
boolean flag를 여러 스레드가 함께 사용할 때
CPU 캐시 때문에 값이 늦게 반영되는 문제 해결
volatile은 “원자성”은 제공하지 않음
단지 가시성(visibility) 문제만 해결
* */
public class VolatileTemplate {

    static class Runner implements Runnable {
        private volatile boolean running = true;

        // 여러 스레드가 최신 값을 읽도록 보장됨
        public void run() {
            while (running) {
                System.out.println("스레드 러닝중");
            }
            System.out.println("스레드 종료됨");
        }

        public void stop() {
            running = false; // 모든 스레드가 즉시 이 값을 본다
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Runner runner = new Runner();
        Thread t = new Thread(runner);
        t.start();

        // main 스레드가 2초 기다렸다가 종료 요청
        Thread.sleep(2000);
        runner.stop();

        t.join();
        System.out.println("main: 프로그램 종료");
    }
}
