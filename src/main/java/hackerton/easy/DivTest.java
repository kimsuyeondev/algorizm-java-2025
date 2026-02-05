package hackerton.easy;

import java.util.Collections;
import java.util.List;

//x의 인수 ? x를 나누었을때 나누어 떨어지는 것!!! x % n == 0
//인수 ? 어떤 수를 나누었을때 나머지가 0 이되는 수
/*
두 개의 정수 배열이 있습니다. 다음 두 조건을 만족하는 모든 정수를 구하세요.

첫 번째 배열의 요소는 모두 고려되는 정수의 인수입니다.
고려되는 정수는 두 번째 배열의 모든 요소의 인수입니다.
이 숫자들은 두 배열 사이에 있다고 합니다 . 이러한 숫자가 몇 개 있는지 확인하세요.

예


배열 사이에 두 개의 숫자가 있습니다.그리고.
,,그리고첫 번째 값에 대해서.
,그리고,두 번째 값에 대해. 반환.

기능 설명

아래 편집기에서 getTotalX 함수를 완성하세요 . 이 함수는 두 집합 사이에 있는 정수의 개수를 반환합니다.

getTotalX에는 다음과 같은 매개변수가 있습니다.

int a[n] : 정수 배열
int b[m] : 정수 배열
보고

int: 집합 사이에 있는 정수의 개수
입력 형식

첫 번째 줄에는 공백으로 구분된 두 개의 정수가 포함됩니다.그리고, 배열의 요소 수그리고.
두 번째 줄에는 다음이 포함됩니다 .서로 다른 공백으로 구분된 정수어디.
세 번째 줄에는 다음이 포함됩니다 .서로 다른 공백으로 구분된 정수어디.
문제이해하기
조건 두 개:
a 배열의 모든 값은 x의 인수이다
→ x % a[i] == 0 (x를 나눴을 때 나머지 0)
x는 b 배열의 모든 값의 인수이다
→ b[j] % x == 0
즉,
“x는 a의 모든 값으로 나눠떨어지고,
b의 모든 값을 나누어 떨어뜨려야 한다.”
이게 전부야.
“두 집합 사이에 있다” 이런 말은 그냥 네이밍일 뿐.
“범위”를 어떻게 잡냐? (여기서 막힌 거지)

a = [2, 6]
b = [24, 36]
아무 수나 x를 다 검사하는 게 아니라,
“이상한 건 애초에 후보에서 빼자” 라고 생각하면 돼.
(1) x는 최소 얼마 이상일까?

조건 1: x % a[i] == 0 이어야 함.
즉, x는 a의 값들을 “다 나눠진다” → x는 a[i]들의 배수.

a = [2, 6]에서
6보다 작은 수가 6의 배수일 수 있어?
→ 절대 없음.

그래서 x는 최소한 max(a) 이상이어야 함.
그래서 시작값:
start = max(a)
이 예제에선 start = 6
이건 공식이 아니라 상식이야:
“어떤 수의 배수”가 되려면, 그 수보다 크거나 같아야 한다.
2) x의 최대값은?
24지 왜냐면 24가 36의 약수가 되지않으니까
* */
public class DivTest {

    public static int getTotalX(List<Integer> a, List<Integer> b) {
        int start = Collections.max(a); // candidate start
        int end = Collections.min(b);   // candidate end

        int cnt = 0;

        for (int n = start; n <= end; n++) {
            boolean ok = true;

            // n must be multiple of all a[i]
            for (int v : a) {
                if (n % v != 0) {
                    ok = false;
                    break;
                }
            }
            if (!ok) continue;

            // all b[j] must be multiple of n
            for (int v : b) {
                if (v % n != 0) {
                    ok = false;
                    break;
                }
            }

            if (ok) {
                cnt++;
            }
        }

        return cnt;
    }

}
