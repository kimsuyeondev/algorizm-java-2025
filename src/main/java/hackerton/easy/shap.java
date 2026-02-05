package hackerton.easy;


public class shap {

    public static void main(String[] args) {
        // Write your code here
        int n = 5;
        int j;
        int space = n - 1; //5
        for (int i = 0; i < n; i++) {
            j = i + 1;
            System.out.print(" ".repeat(space));
            space -= 1;
            System.out.print("#".repeat(j));
            System.out.println();
        }


    }
}
