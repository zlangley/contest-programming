import java.util.Arrays;
import java.util.Scanner;

public class Problem1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int[] red = new int[n];
        int[] green = new int[n];

        for (int i = 0; i < n; i++)
            red[i] = scan.nextInt();

        for (int i = 0; i < n; i++)
            green[i] = scan.nextInt();

        Arrays.sort(red);
        Arrays.sort(green);

        for (int i = 0; i < n; i++)
            if (red[i] >= green[i]) {
                System.out.println("NO");
                return;
            }

        System.out.println("YES");
    }
}
