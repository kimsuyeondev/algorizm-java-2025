package hackerton.thread;
/*
조건 충족 전까지 기다리게 하고,
다른 스레드가 notify() 하면 다시 실행 재개.

 사용 조건

반드시 synchronized 블록 안에서 사용해야 함

2) wait() / notify() — 스레드를 잠재웠다가 조건 충족 시 깨우기
 언제 쓰나?
스레드들이 순서대로 실행해야 할 때
Producer/Consumer 패턴
Print in Order 문제
FooBar 번갈아 출력 문제
 wait/notify는 반드시 synchronized 안에서만 호출 가능
* */
public class ThreadMethodTemplate {
    private boolean ready = false;

    // 어떤 스레드가 조건을 기다리는 메서드
    public synchronized void waitUntilReady() throws InterruptedException {
        //  조건이 false면 계속 기다린다
        System.out.println("waitUntilReady!!!기다리기시작");
        while (!ready) {
            System.out.println("waitUntilReady!!!곧잠듬");
            wait(); // 스레드가 여기서 잠든다 (락은 자동으로 반환)
        }

        // ready == true가 되면 깨어나서 아래 코드를 실행
        System.out.println("깨어나서 작업 실행!");
    }

    // 다른 스레드가 조건을 true로 만들고 깨우는 메서드
    public synchronized void setReady() {
        ready = true;  // 조건 변경
        System.out.println("이제일해!");
        notify();      // 🔊 기다리던 스레드 중 하나를 깨움
    }


    public static void main(String[] args) throws InterruptedException {
        ThreadMethodTemplate work = new ThreadMethodTemplate();

        //쓰레드안에 러너블넣은거지
        Thread worker = new Thread(() -> {
            try {                work.waitUntilReady();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        worker.start();

        // main 스레드가 잠깐 딴 일 하는 척
        Thread.sleep(2000);
        System.out.println("main: 이제 ready를 true로 바꿈");

        work.setReady();

        worker.join();
        System.out.println("main: 모든 작업 종료");
    }

}

