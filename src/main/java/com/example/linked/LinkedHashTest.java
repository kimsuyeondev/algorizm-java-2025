package com.example.linked;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
문제: 배차 지역 LRU 캐시 시뮬레이션
  배차 시스템을 운영하고 있다.
 배차 서버는 최근에 많이 배차된 지역의 정보를 캐시에 올려두고,
 같은 지역에서 다시 호출이 오면 캐시를 사용해 빠르게 처리하려고 한다.

캐시 규칙 (LRU – Least Recently Used)
 캐시는 최대 cacheSize개의 지역(region) 을 저장할 수 있다.
 새로운 배차 요청이 들어올 때마다 다음을 수행한다:
 캐시 HIT:
 해당 지역이 이미 캐시에 있으면 → 바로 사용
 처리 비용은 1
 이 지역은 “가장 최근에 사용된 지역”이 된다.
 캐시 MISS:
 해당 지역이 캐시에 없으면 → 외부 시스템에서 정보를 가져와 캐시에 넣음
 처리 비용은 5
 캐시가 가득 차 있다면 → 가장 오래 사용되지 않은 지역(LRU) 을 캐시에서 제거하고 새 지역을 넣는다.
 지역 이름은 대소문자를 구분하지 않는다. (예: "SEOUL" 과 "seoul" 은 같은 지역으로 간주)
 *
 순서가있을 경우
 LinkedHashMap
 LinkedHashMap<K, V> cache = new LinkedHashMap<>(16, 0.75f, true);

 순서가 있고 중복이 제거
 LinkedHashSet
 *
 * */
/**
 * @param cacheSize 캐시 최대 크기 (0 이상)
 * @param regions   배차 요청이 들어온 지역 이름 배열 (예: ["Seoul", "Busan", "Seoul", ...])
 * @return          모든 요청을 처리하는 데 걸린 총 비용
 */

public class LinkedHashTest {
    public static void main(String[] args) {

        int cacheSize = 3;
        String[] regions   = {"Seoul", "Busan", "Seoul", "Daegu", "Seoul", "Busan"};
        //  daegu -> seoul -> busan
        //  ccc -> 222 -> 1 -> 111 -> ddd
        Solution solution = new Solution();
        System.out.println(solution.solution(cacheSize, regions));;
        System.out.println("---");
        LinkedHashMap<String, String> cache = new LinkedHashMap<>(16, 0.75f, true);
        cache.put("ccc","cat");
        cache.put("ddd","dog");
        cache.put("222","222");
        cache.put("1","1");
        cache.put("111","111");
        cache.get("ddd");
        for(String key : cache.keySet()) {
            System.out.println(key);
        }
    }

}
 /**
 * @param cacheSize 캐시 최대 크기 (0 이상)
 * @param regions   배차 요청이 들어온 지역 이름 배열 (예: ["Seoul", "Busan", "Seoul", ...])
 * @return          모든 요청을 처리하는 데 걸린 총 비용
 */
class Solution {
    public int solution(int cacheSize, String[] regions) {
        if(cacheSize == 0) {
            return regions.length * 5;
        }
        LinkedHashMap<String,String> map = new LinkedHashMap<>(cacheSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                return size() > cacheSize;
            }
        };

        int totalAmt = 0;
        for(String region : regions) {
            region = region.toLowerCase();
            if(map.containsKey(region)) {
                totalAmt += 1;
                map.get(region);
            }else {
                map.put(region,region);

                totalAmt+=5;
            }
        }

        // 가장 최근 지역
        List<String> recentRegions = new ArrayList<>(map.keySet());
        Collections.reverse(recentRegions);
        recentRegions.stream().forEach(a-> System.out.println(a));


        return totalAmt;
    }

}

