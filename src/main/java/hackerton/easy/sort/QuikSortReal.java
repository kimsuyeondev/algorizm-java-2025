package hackerton.easy.sort;

import java.util.*;

public class QuikSortReal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        quickSort(arr, 0, n - 1);
    }

    // 제자리 퀵소트
    static void quickSort(int[] arr, int low, int high) {
        // 길이 1 이하 구간은 정렬/출력 X
        if (low >= high) return;

        int p = partition(arr, low, high); // Lomuto partition
        printArray(arr);                   // partition이 끝난 후 전체 배열 출력

        // 왼쪽 먼저
        quickSort(arr, low, p - 1);
        // 그 다음 오른쪽
        quickSort(arr, p + 1, high);
    }

    // Lomuto partitㅇion: pivot = arr[high]
    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low;  // pivot보다 작은 값이 들어갈 위치

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                // 작은 값 발견 → i 위치와 swap
                swap(arr, i, j);
                i++;
            }
        }

        // 마지막에 pivot을 i 위치로 옮김
        swap(arr, i, high);
        return i;  // pivot의 최종 위치
    }

    static void swap(int[] arr, int a, int b) {
        if (a == b) return;
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    static void printArray(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < arr.length; k++) {
            if (k > 0) sb.append(" ");
            sb.append(arr[k]);
        }
        System.out.println(sb.toString());
    }
}
