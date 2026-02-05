package hackerton.thread;

import java.util.concurrent.Semaphore;

public class SemaphoreTemplate {
    static class FooBar {
        private int n;
        private Semaphore fooSem = new Semaphore(1); // Foo 먼저 실행
        private Semaphore barSem = new Semaphore(0); // Bar는 Foo 끝나야 실행

        public FooBar(int n) {
            this.n = n;
        }

        public void printFoo() {
            try {
                for (int i = 0; i < n; i++) {
                    fooSem.acquire(); // Foo 차례 기다림
                    System.out.print("Foo");
                    barSem.release(); // Bar 실행 허용
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void printBar() {
            try {
                for (int i = 0; i < n; i++) {
                    barSem.acquire(); // Bar 차례 기다림
                    System.out.println("Bar");
                    fooSem.release(); // 다음 Foo 허용
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FooBar fooBar = new FooBar(5); // FooBar 5번 찍기

        Thread t1 = new Thread(fooBar::printFoo);
        Thread t2 = new Thread(fooBar::printBar);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("끝!");
    }
}
