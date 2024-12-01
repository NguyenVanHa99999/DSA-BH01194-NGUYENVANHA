package DataStructure;

public class Array {
    public static void main(String[] args) {
        // Bài tập số 2
        String[] students = {
                "BH01178,9,8.5,10,10,7,9,9.5,10",
                "BH01144,9,8.5,10,9,7.5,9,8,10",
                "BH01244,7,8.5,8,9,7.5,7.5,8,10",
        };
        // for (String s : students) {
        // getAverage(s);
        // }

        // Copy array
        // int[] a = { 2, 4, 6, 8 };
        // int[] b = new int[4];
        // // b = a;
        // // b[0] = 100;
        // // System.out.println(a);
        // // System.out.println(b);
        // // System.out.println(Arrays.toString(a));
        // System.arraycopy(a, 0, b, 0, a.length);
        // System.out.println(Arrays.toString(b));

    }

    public static void getAverage(String student) {
        String[] a = student.split(",");
        float sum = 0;
        for (int i = 1; i < a.length; i++) {
            sum = sum + Float.parseFloat(a[i]);
        }
        float average = sum / (a.length - 1);
        System.out.print("Sinh viên:");
        System.out.println(a[0]);
        System.out.print("Điểm TB:");
        System.out.println(average);
    }
}