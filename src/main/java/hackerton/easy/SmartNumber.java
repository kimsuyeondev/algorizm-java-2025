package hackerton.easy;

public class SmartNumber {
/*
int root = (int)Math.sqrt(n); 4의 제곱근 2 6의제곱근 2.34343543 어쩌고
if (root * root == n) → 완전 제곱수! 2*2 = 4 즉 6이아니지 그러면 이 제곱수에 해당되면 약수가 홀수이다!!!!

* 약수는 대부분 짝수다
* 그러나 홀수가 간혹있다
* 1 13 169
* 1 2 4
*
* 보통 제곱근으로 많이 구한다 제곱근으로 나누어 떨어지면 홀수
*
* 1 2 3 6
* 완전 제곱근이라면 홀수개
* 완전 제곱근이 아니라면 짝수개
*
* 루트 4는 2
* 4%2 = 2  == 0
*---
* 약수할때 제곱근만 포문을 돌리는 이유 ?
* 약수 쌍:
*---
36 =
1 × 36
2 × 18
3 × 12
4 × 9
6 × 6   ← 제곱근이 중앙 제곱근부터는 중복이라 체크안해도된다.
9 × 4   ← 반복
12 × 3  ← 반복
18 × 2  ← 반복
36 × 1  ← 반복
*/
    public static boolean isSmartNumber(int num) {
        int val = (int) Math.sqrt(num);
        System.out.println(val);
        if (num % val == 0)
            return true;
        return false;
    }

    public static int isDivisor(int num) {
        //6*6 36보다작을때
        int count = 0;

        for (int i = 1; i * i <= num; i++) {
            if (num % i == 0) {
                count++; //나눠지면 약수
                if (i != num / i) { //i*i=num 아이곱하기아이는 오버플로우 근데 포문안에서 안쓰면 이건 틀린식임 i*i=num으로해야함,  // 제곱근이아니라면 짝수개 추가
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(isDivisor(36));
        System.out.println(isDivisor(4));
        System.out.println(isSmartNumber(6));
    }
}
