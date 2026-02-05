package com.practice.rule;
/*
문제: 쿠폰 코드 기반 택시 요금 계산기
택시 결제에 쿠폰을 적용할 수 있다.
아래는 쿠폰 코드와 그에 해당하는 할인 규칙이다:
쿠폰코드	할인 규칙
WELCOME10	10% 할인
NIGHT3000	심야시간(22~05시)에만 사용 가능 + 3,000원 할인
BASIC1000	시간 무관 1,000원 할인
DRIVER50	50% 할인, 단 최대 5,000원까지
기본 요금: baseFare (int)
승차 시각: hh:mm (24시간)
쿠폰코드: couponCode (문자열, null 또는 "" 가능)
최종 결제 금액 (int, 최소 0원)
 예시
기본 요금: 12000
시간: 23:10
쿠폰: NIGHT3000
→ 심야시간이므로 3000원 할인 → 9000원
*/

import java.util.HashMap;
import java.util.Map;

interface DiscountRule {
    int apply(int fee, int hour);
}
public class RuleTest {

    public static int solution(int baseFare, String time, String couponCode) {
        String[] t = time.split(":");
        int h = Integer.parseInt(t[0]);
        Map<String, DiscountRule> resultFee = new HashMap<>();

        resultFee.put("WELCOME10", (fee, hour) -> {
            return (int)(fee * 0.9);
        });

        resultFee.put("NIGHT3000", (fee, hour) -> {
            if(hour >= 22 || hour <= 5){
                return fee - 3000;
            }
            return fee;
        });

        resultFee.put("BASIC1000", (fee, hour) -> {
           return fee - 1000 ;
        });

        resultFee.put("DRIVER50", (fee, hour) -> {
            int discount = (int) (fee * 0.5);
            discount = Math.min(discount, 5000);
            return fee - discount;
        });

       DiscountRule rule = resultFee.get(couponCode);
       if(rule == null) {
            return baseFare;
       }
       int finalFee = rule.apply(baseFare, h);

       return Math.max(finalFee, 0); // 최소 0원
    }

    public static void main(String[] agrs) {
        int baseFare = 12_000;
        String time = "23:10";
        String couponCode = "BASIC1000";

        System.out.println(solution(baseFare, time, couponCode));


    }
}
