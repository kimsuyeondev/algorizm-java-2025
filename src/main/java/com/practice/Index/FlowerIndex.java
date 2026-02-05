package com.practice.Index;

/*
1“1년을 배열로 구성하고 꽃이 피는 구간의 인덱스를 제어하는 문제”
이건 날짜/구간 시뮬레이션 문제의 변형이다.
문제 예시
1년 = 365일 배열(1일 → index 1~365)
꽃이 피는 기간 리스트가 주어진다.
각 기간은 [startDay, endDay] (둘 다 포함)
다음 조건을 만족하는 꽃이 피는 전체 날짜 수를 구하라:
같은 날짜에 여러 꽃이 피어도 하루는 1번만 센다.
구간이 겹쳐도 하나의 연속 기간으로 처리한다.
입력 예시
intervals = [
        [10, 20],
        [15, 18],
        [30, 35],
        [34, 40]
        ]
출력 예시
20
구간:
[10~20]
[15~18] → 10~20 안에 포함 → 합치기
[30~35]
[34~40] → 합쳐서 30~40
결과 구간:
10~20 → 11일
//30~40 → 11일
→ 총 22일
 */
public class FlowerIndex {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] intervals = {
                {10, 20},
                {15, 18},
                {30, 35},
                {34, 40}};
        System.out.println(solution.solution(intervals));
    }
}

class Solution {
/*
20
구간:
[10~20]
[15~18] → 10~20 안에 포함 → 합치기
[30~35]
[34~40] → 합쳐서 30~40
결과 구간:
10~20 → 11일
//30~40 → 11일
→ 총 22일
*/
    public int solution(int[][] intervals) {
        int cnt = 0;
        boolean[] bloomday = new boolean[366];

        for (int i = 0; i < intervals.length; i++) {
            int start = intervals[i][0];
            int end = intervals[i][1];
            bloomday[start] = true;
            for(int j = start; j<=end; j++) {
                bloomday[j] = true;
            }
        }

        for(int i=1; i <bloomday.length; i++) {
            if(bloomday[i]) {
                cnt ++;
            }
        }
        return cnt;
    }
}
