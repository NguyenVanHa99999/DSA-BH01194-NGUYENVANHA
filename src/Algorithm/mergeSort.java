package Algorithm;
import java.util.Arrays;
public class mergeSort {
    public static void mergeSort(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, arr.length);
        
        mergeSort(left);
        mergeSort(right);
        
        merge(arr, left, right);
    }
    public static void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }
        while (i < left.length) {
            arr[k++] = left[i++];
        }
        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }
    public static void main(String[] args) {
        int n = 100000000; 
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int)(Math.random() * 1000); 
        }
        long startTime = System.nanoTime();  
        mergeSort(arr);                      
        long endTime = System.nanoTime();    

        long duration = endTime - startTime;  
        System.out.println("Merge Sort Time: " + duration + " nanoseconds");
    }

};