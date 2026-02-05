package hackerton.easy;

public class Jump {
//x1 v1 x2 v2 주어졌을때
    // 5 2 0 3 이면
    // 5 7  9  11  13  15
    // 0 3  6   9  12  15

    //수학공식을 잘생각해야함
    // 5 + 2 + 2 + 2 + 2  =  5 + 2*n
    // 0 + 3 + 3 + 3 + 3  =  5 + 3*n
    // x1 + v1*n = x2 + v2*n
    // v1n - ( v2*n )= x2 - x1
    // n(v1-v2) = x2 -x1
    // n = (x2-x1)/v1-v2 % == 0이면오케이

    public static String kangaroo2(int x1, int v1, int x2, int v2) {
        // Write your code here
        if((x2 > x1 && v2 > v1)) {
            return "NO";
        }

        if( (x2 - x1) % (v1-v2) == 0 ) {
            return "YES";
        }
        return "NO";
    }
    public static String kangaroo(int x1, int v1, int x2, int v2) {


        // Write your code here
        if((x2 > x1 && v2 > v1)) {
            return "NO";
        }

        // x1=0 3 6 9 12 v1=3
        // x2=4 6 8 12   v2=2
        //x1 = 5, v1 = 2
        //x2 = 0, v2 = 3
        if(v1 < v2){
            while(x1 > x2) { //
                x2 += v2;
                x1 += v1;
                if(x1 == x2) {
                    return "YES";
                }
            }
        } //3 > 2
        if(v1 > v2) {
            while(x1 < x2) {
                x2 += v2; //0 3 6 9 12
                x1 += v1; //4 6 8 10 12
                if(x1 == x2) {
                    return "YES";
                }
            }
        }

        return "NO";
    }

    public static void main(String[] args) {
        //x1 = 5, v1 = 2
        //x2 = 0, v2 = 3
        System.out.println(kangaroo(5,2,0,3));
    }
}
