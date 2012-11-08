import java.util.Scanner;

public class Chocolate {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }

        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = arr[0];
        right[n-1] = arr[n-1];

        for (int i = 1; i < n; i++) {
            left[i] = arr[i] + left[i-1];
            right[n-i-1] = arr[n-i-1] + right[n-i];
        }

        int alice = 0;
        while (alice < n && left[alice] <= right[alice])
            alice++;

        System.out.println(alice + " " + (n - alice));
    }
}
