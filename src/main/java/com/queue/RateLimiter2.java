package com.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class RateLimiter2 {

    private final int limit;
    private final int windowSec;
    private final Deque<Long> allowed = new ArrayDeque<>();

    public RateLimiter2(int limit, int windowSec) {
        this.limit = limit;
        this.windowSec = windowSec;
    }

    public boolean allow() {
        long now = System.currentTimeMillis();
        cleanup(now);

        if (allowed.size() < limit) {
            allowed.addLast(now);
            return true;
        }
        return false;
    }

    private void cleanup(long now) {
        long limitTime = now - windowSec * 1000L;
        while (!allowed.isEmpty() && allowed.peekFirst() < limitTime) {
            allowed.pollFirst();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 5초 동안 최대 3건
        RateLimiter2 limiter = new RateLimiter2(3, 5);

        for (int i = 1; i <= 5; i++) {
            System.out.println("Request " + i + " allowed? " + limiter.allow());
            Thread.sleep(1000);
        }

        // 5초 안에 3건 초과하면 false 나오는 걸 볼 수 있음
    }
}

