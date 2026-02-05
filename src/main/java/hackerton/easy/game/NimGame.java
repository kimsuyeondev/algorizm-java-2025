package hackerton.easy.game;
/*
* 안해봄 님게임인데
*
* xor 로 푸는거임
* 3,4 이면 3xor
* 4xor ..누적해서
*
* 0이나올때도잇더라~
*
* */
public class NimGame {
    String nimGame(int[] pile) {
        int xor = 0;
        for (int p : pile) xor ^= p;

        return xor == 0 ? "Second" : "First";
    }

}
