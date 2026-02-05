package hackerton.easy.game;
/*
1~15까지 판을 다 그려서
a가 지는경우를 판단해봄
그러고나서 규칙을 보면됨

* */
public class Chessboard {
    public static String chessboardGame(int x, int y) {
        // Write your code here
        if((x%4==1||x%4==2) && (y%4==1 || y%4==2)) {
            return "Second";
        }
        return "First";
    }
}
