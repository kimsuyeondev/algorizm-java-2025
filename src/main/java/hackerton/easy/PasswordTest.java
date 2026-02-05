package hackerton.easy;

import java.util.regex.Pattern;

import java.io.*;

class Result3 {
    /*
     * Complete the 'minimumNumber' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. STRING password
     */
    public static int minimumNumber(int n, String password) {
        int failCnt = 0;


        String number = "0123456789";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String special = "!@#$%^&*()-+";


        boolean hasNumber = false;
        for(char c : number.toCharArray()){
            String a = String.valueOf(c);

            if(password.contains(a)){
                hasNumber = true;
            }
        }

        boolean hasLower = false;
        for(char c : lower.toCharArray()){
            String a = String.valueOf(c);
            if(password.contains(a)){
                hasLower = true;
            }
        }
        boolean hasUpper = false;
        for(char c : upper.toCharArray()){
            String a = String.valueOf(c);

            if(password.contains(a)){
                hasUpper = true;
            }
        }
        boolean hasSpecial = false;
        for(char c : special.toCharArray()){
            String a = String.valueOf(c);
            System.err.println("special=" + a);
            if(password.contains(a)){

                System.err.println("special=???" + a);
                hasSpecial = true;
            }
        }
        System.err.println(hasSpecial);
        if(!hasNumber){
            failCnt++;
            System.err.println("1failCnt="+failCnt);
        }
        if(!hasLower) {
            failCnt++;
            System.err.println("f2ailCnt="+failCnt);
        }
        if(!hasUpper) {
            failCnt++;
            System.err.println("3failCnt="+failCnt);
        }

        if(!hasSpecial){
            failCnt++;
            System.err.println("4failCnt="+failCnt);
        };

        System.err.println("5failCnt="+failCnt);
        int length = 0;
        if(password.length() < 6 ) {
            length = 6-password.length();
        }

        return Math.max(failCnt, length);

    }


    public static int minimumNumber2(int n, String password) {
        int failCnt = 0;

        Pattern numberPattern = Pattern.compile("[0-9]");
        Pattern lowerPattern = Pattern.compile("[a-z]");
        Pattern upperPattern = Pattern.compile("[A-Z]");
        //여기서 \\ 로 해야함 -가 무넺임  -는 부터라는의미라서
        Pattern specialPattern = Pattern.compile("[!@#$%^&*()\\-+]");

        boolean hasNumber = false;
        if(numberPattern.matcher(password).find()){
            hasNumber = true;
        }

        boolean hasLower = false;
        if(lowerPattern.matcher(password).find()){
            hasLower = true;
        }
        boolean hasUpper = false;
        if(upperPattern.matcher(password).find()){
            hasUpper = true;
        }
        boolean hasSpecial = false;
        if(specialPattern.matcher(password).find()){
            hasSpecial = true;
        }
        System.err.println(hasSpecial);
        if(!hasNumber){
            failCnt++;
            System.err.println("1failCnt="+failCnt);
        }
        if(!hasLower) {
            failCnt++;
            System.err.println("f2ailCnt="+failCnt);
        }
        if(!hasUpper) {
            failCnt++;
            System.err.println("3failCnt="+failCnt);
        }

        if(!hasSpecial){
            failCnt++;
            System.err.println("4failCnt="+failCnt);
        };

        System.err.println("5failCnt="+failCnt);
        int length = 0;
        if(password.length() < 6 ) {
            length = 6-password.length();
        }

        return Math.max(failCnt, length);

    }

}

public class PasswordTest {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String password = bufferedReader.readLine();

        int answer = Result3.minimumNumber(n, password);

        bufferedWriter.write(String.valueOf(answer));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
