package hackerton.thread;

public class SynchronizedTemplate {

    static class Counter {
        private int count = 0;

        // 방법 1: 메서드 전체에 synchronized
        public synchronized void increase() {
            count++;
        }

        // 방법 2: 블록에 synchronized(this)
        public void increaseBlock() {
            synchronized (this) {
                count++;
            }
        }

        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        // 스레드 2개가 동시에 count를 10_000번씩 증가
        //Runnable 은 작업내용
        Runnable task = () -> {
            for (int i = 0; i < 10_000; i++) {
                counter.increase(); // 혹은 counter.increaseBlock();
            }
        };

//쓰레드에 작업내용넣음
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);

        t1.start();
        t2.start();

        t1.join(); // t1 끝날 때까지 대기
        t2.join(); // t2 끝날 때까지 대기

        System.out.println("최종 count = " + counter.getCount());
        // synchronized 없으면 20000보다 작게 나올 수 있음 (깨짐)
        // synchronized 있으면 항상 20000
    }
}
