package Algorithm;

public class bubbleSort {

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                  
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
       
        int n = 1000; 
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (int)(Math.random() * 1000); 
        }

        long startTime = System.nanoTime(); 
        bubbleSort(arr);                      
        long endTime = System.nanoTime();    

        long duration = endTime - startTime;  
        System.out.println("Bubble Sort Time: " + duration + " nanoseconds");
    }
}

