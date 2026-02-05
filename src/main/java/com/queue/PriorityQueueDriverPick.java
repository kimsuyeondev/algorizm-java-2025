package com.queue;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
PriorityQueue
N명 기사 중 "거리 → rating → driverId" 순서로 가장 좋은 기사 1명을 선택.
큐는 pollFirst, addLast, peekFirst()
스택은 푸시 팝 픽
우선순위 큐는 오퍼, 폴 우선순위 기준 자동 정렬됨
 */
class Driver {
    String id;
    int dist;
    double rating;

    public Driver(String id, int dist, double rating) {
        this.id = id;
        this.dist = dist;
        this.rating = rating;
    }


}

public class PriorityQueueDriverPick {

    public static void main(String[] args) {

        Driver[] drivers = {
                new Driver("driverA", 500, 4.5),
                new Driver("driverB", 300, 4.2),
                new Driver("driverC", 300, 4.8),
                new Driver("driverD", 300, 4.8),
                new Driver("driverE", 1000, 5.0)
        };

        Driver best = pickBest(drivers);
        System.out.println("선택된 기사: " + best.id);
        // 기대:
        // 거리 300 중 → 평점 4.8 두 명 → id 사전순으로 driverC

        //상위 k명 기사
        int k = 7;
        String[] kDrivers = topDriver(drivers, k);
        System.out.println(Arrays.toString(kDrivers));
    }

    public static Driver pickBest(Driver[] drivers) {
        PriorityQueue<Driver> queue = new PriorityQueue<>(
                (a, b) -> {
                    if (a.dist != b.dist) return a.dist - b.dist;//거리가 가까운순
                    if (a.rating != b.rating) return Double.compare(b.rating, a.rating); //내림차순
                    return a.id.compareTo(b.id);
                });

        for (Driver driver : drivers) {
            queue.offer(driver);
        }
        return queue.poll();
    }

    public static String[] topDriver(Driver[] drivers, int k) {

        PriorityQueue<Driver> queue = new PriorityQueue<>(
                (a, b) -> {
                    if (a.dist != b.dist) return a.dist - b.dist; //거리가 가까운순
                    if (a.rating != b.rating) return Double.compare(b.rating, a.rating); //내림차순
                    return a.id.compareTo(b.id);
                }
        );
        for (Driver driver :drivers) {
            queue.offer(driver);
        }
        // k가 전체 기사 수보다 클 수도 있으니까 방어
        k = Math.min(k, drivers.length);
        String[] result = new String[k];

        for (int i = 0; i < k; i++) {
            if(!queue.isEmpty()) {
                Driver driver = queue.poll();
                result[i] = driver.id;
            }
        }

        return result;
    }

}
