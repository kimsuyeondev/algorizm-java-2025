package com.algorizm.dp.dp;

/*
문자열 최소 편집 거리(Edit Distance) 문제
- 삽입, 삭제, 교체 3가지 연산
- 문자열 A를 B로 바꾸는 최소 연산 횟수를 구하라

두 문자열 A, B가 있다.
A를 B로 바꾸려면 아래 3가지 연산을 사용할 수 있다:
삽입 (Insert)
삭제 (Delete)
교체 (Replace)
이 3가지 연산을 최소 몇 번 사용해야 두 문자열을 같게 만들 수 있는지를 구하라.
a = "cat"
b = "cut"
a와 b의 다른 점: a → u 로 교체
필요 연산: 1
정답: 1
a = "sunday"
b = "saturday"
3

연산	의미	참조하는 이전 상태
삭제(Delete)	    A의 마지막 글자 제거	dp[i-1][j]
삽입(Insert)	    B의 마지막 글자 생성	dp[i][j-1]
교체(Replace)	A[i-1] → B[j-1]	    dp[i-1][j-1]
*/

public class EditDistanceDP {

    public static void main(String[] args) {
        String a = "sunday";
        String b = "saturday";

        int result = solve(a, b);
        System.out.println("최소 편집 거리: " + result);
    }

    public static int solve(String a, String b) {
        int n = a.length();
        int m = b.length();

        int[][] dp = new int[n + 1][m + 1];
        // 1) dp 배열 초기화
        //    - dp[i][0] = i   (모두 삭제)
        //    - dp[0][j] = j   (모두 삽입)
        // 초기화: 한쪽이 빈 문자열일 때
        for (int i = 0; i <= n; i++) dp[i][0] = i; // 모두 삭제 A의 앞 i글자 → 빈 문자열("") 로 바꾸는 최소 연산 수
        for (int j = 0; j <= m; j++) dp[0][j] = j; // 모두 삽입 B의 앞 j글자 로 바꾸는 최소 연산 수

        // 2) 점화식 채우기
        //    if (a[i-1] == b[j-1]) dp[i][j] = dp[i-1][j-1];
        //    else dp[i][j] = 1 + min( dp[i-1][j], dp[i][j-1], dp[i-1][j-1] );
        for( int i=1; i<=n; i++) { //i는 A에서 현재 보고 있는 부분 길이
            for(int j=1; j<=m; j++) { //j는 B에서 현재 보고 있는 부분 길이
                if(a.charAt(i-1) == b.charAt(j-1)) { //i=1, j=1 ⇒ 'c' vs 'c'
                    dp[i][j] = dp[i - 1][j - 1]; // 그대로
                }else {
                    dp[i][j] = 1 + Math.min(dp[i-1][i] //삭제
                            ,Math.min(dp[i][j-1], dp[i-1][j-1]));
                }
            }
        }
        return dp[n][m]; // 임시
    }
}
