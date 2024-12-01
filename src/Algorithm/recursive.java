package Algorithm;

public class recursive {

    public static int sum(int n) {
        // Base case
        if (n == 0) {
            return 0;
        }
        // int s = n + sum(n - 1);
        // return s;
        return n + sum(n - 1);
    }

    public static int giaithua(int n) {
        // Base case
        if (n == 0) {
            return 1;
        }
        // Recursive case
        return n * giaithua(n - 1);
    }

    public static int fibonaci(int n) {
        // Base case
        if (n <= 1) {
            return n;
        }
        // Recursive case fibonaci(n - 1) + fibonaci(n - 2)
        return fibonaci(n - 1) + fibonaci(n - 2);
    }

}
