package hackerton.easy.sort;

import java.util.*;

public class CountingSort3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int cnt = scanner.nextInt();
        List<Integer> list = new ArrayList<>();
        
        while(scanner.hasNextLine()){
            String value = scanner.nextLine();
            
            if(value.trim().isEmpty()) continue;
            
            String[] parts = value.trim().split(" ");
            
            list.add(Integer.parseInt(parts[0]));
        }
        
        int[] array = new int[100];
        
        for(int i=0; i<list.size(); i++) {
            int idx = list.get(i);
            array[idx] ++;    
        }
        
        int[] result = new int[100];
        
        for(int i=0; i<array.length; i++) {
            int value = array[i];
            if(i > 0) {
                result[i] = value + result[i-1];
            }else{
                result[0] = value;
            }
        }
        for(int num : result) {
            System.out.print(num + " ");
        }
    }
}
