package com.algorizm.slidingwindow;

import java.util.ArrayDeque;
import java.util.Deque;

/**
  * 최근 W초 동안 최대 M개만 처리할 수 있을 때,
 * 실제 처리된 요청 개수를 구하는 예제.
 *
 문제: 요청 처리 제한 (Rate Limiting)
 로그 서버에 요청이 시간순으로 들어온다.
 각 요청의 시간은 초 단위 정수로 주어진다. (timestamps[i])
 시스템은
 “최근 W초 동안 최대 M개의 요청만 처리할 수 있다.”
 라는 정책을 따른다.
 각 요청에 대해 순서대로
 정책에 따라 처리 가능한 요청은 처리하고,
 초과한 요청은 버린다(거절)
 라고 할 때,
 실제로 처리된 요청의 개수를 구하라.
 예)
 W = 10, M = 3
 timestamps = [1, 2, 3, 11, 12, 13, 14]
 처리 과정:
 t=1 → 처리 (최근 10초 = [1]) → 1개
 t=2 → 처리 (최근 10초 = [1,2]) → 2개
 t=3 → 처리 (최근 10초 = [1,2,3]) → 3개
 t=11 → 최근 10초 = [2,3,11] → 처리 OK
 t=12 → 최근 10초 = [3,11,12] → 처리 OK
 t=13 → 최근 10초 = [11,12,13] → 처리 OK
 t=14 → 최근 10초 = [11,12,13,14] → 4개 → 최대 3개 초과 → 거절
 → 최종 처리 개수 = 6
 */
public class RateLimiting {

    // 슬라이딩 윈도우 기반 Rate Limiter
    static class SlidingWindowRateLimiter {
        private final int windowSeconds;  // 최근 몇 초?
        private final int maxRequests;    // 그 구간에 허용할 최대 요청 수
        private final Deque<Integer> window = new ArrayDeque<>();

        public SlidingWindowRateLimiter(int windowSeconds, int maxRequests) {
            this.windowSeconds = windowSeconds;
            this.maxRequests = maxRequests;
        }

        // timestamp: 초 단위 (1,2,3,...)
        public boolean allowRequest(int timestamp) {
            // 이번 요청 기준으로 "살아있는 구간"의 시작 시각
            int threshold = timestamp - windowSeconds + 1;

            // 윈도우 밖(너무 오래된 요청들) 제거
            while (!window.isEmpty() && window.peekFirst() < threshold) {
                window.pollFirst();
            }

            // 이 시점에서 window.size() = "최근 windowSeconds초 동안의 요청 개수"
            if (window.size() >= maxRequests) {
                // 이미 꽉 찼으므로 이 요청은 거절
                return false;
            }

            // 이번 요청을 윈도우에 추가하고 허용
            window.offerLast(timestamp);
            return true;
        }

        // "최근 X초 요청 개수" 직접 보고 싶을 때 쓸 수 있는 함수
        public int recentCount() {
            return window.size();
        }
    }

    // 코딜리티 스타일이라면 보통 이런 형태:
    // public int solution(int[] timestamps, int W, int M) { ... }
    public static int countAccepted(int[] timestamps, int windowSeconds, int maxRequests) {
        SlidingWindowRateLimiter limiter = new SlidingWindowRateLimiter(windowSeconds, maxRequests);
        int accepted = 0;

        for (int t : timestamps) {
            if (limiter.allowRequest(t)) {
                accepted++;
            }
        }

        return accepted;
    }

    public static void main(String[] args) {
        int[] timestamps = {1, 2, 3, 11, 12, 13, 14};
        int W = 10;
        int M = 3;

        int accepted = countAccepted(timestamps, W, M);
        System.out.println("실제로 처리된 요청 수: " + accepted);  // 기대: 6

        // 동작 과정 디버깅용으로 보고 싶으면 이렇게도 가능
        SlidingWindowRateLimiter limiter = new SlidingWindowRateLimiter(W, M);
        for (int t : timestamps) {
            boolean allowed = limiter.allowRequest(t);
            System.out.println("t=" + t +
                    ", allow=" + allowed +
                    ", recentCount=" + limiter.recentCount());
        }
    }
}
