package hackerton.easy.game;
/*
//[a코드]
모든 더미가 1개짜리인 경우 (예: [1,1], [1,1,1] 같은 경우)
더미 개수 n이 짝수면 → First 승
더미 개수 n이 홀수면 → Second 승
1개보다 큰 더미가 하나라도 있는 경우
이땐 일반 Nim이랑 똑같이 판단해도 된다
즉, 일반 Nim 규칙 + “전부 1일 때만 예외” 딱 이거야.

일반 nim규칙은 xor
일반 Nim의 승리 조건 = 모든 더미를 XOR 했을 때 0인지 아닌지

Nim 게임인지 판단하는 기준 3가지 (이것만 기억하면 된다)
① 여러 개의 독립된 더미(=heap)가 존재하는가?
→ 예: 돌더미, 막대, 탑, 숫자리스트 등
② 매 턴에 한 더미만 선택해서 그 값(크기)을 줄이는가?
→ 예: 돌 제거, 길이 감소, 수 줄이기
③ 마지막 행동이 승/패를 결정하는가?
→ 예: 마지막 돌을 가져가면 승/패
이 3개 전부 만족하면 = Nim or misère Nim
2개만 만족해도 Grundy 기반 게임일 가능성 매우 높음.
* */
public class MisereNim {
    String misereNim(int[] s) {
        int n = s.length;

        boolean allOnes = true; //a코드쓸건지
        for (int stones : s) {
            if (stones != 1) {
                allOnes = false;
                break;
            }
        }

        if (allOnes) {//a코드쓸건지
            // 모든 더미가 1개짜리
            if (n % 2 == 0) {
                return "First";  // 짝수개 → First 승
            } else {
                return "Second"; // 홀수개 → Second 승
            }
        } else {
            // 일반 Nim 규칙 적용
            int xor = 0;
            for (int stones : s) {
                xor ^= stones;
            }
            if (xor == 0) return "Second";
            else return "First";
        }
    }

}
