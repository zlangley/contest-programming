import java.util.Scanner;

public class Potatoes {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int y = scan.nextInt();
        int k = scan.nextInt();
        int n = scan.nextInt();

        StringBuilder res = new StringBuilder();
        for (int x = k - (y%k); x + y <= n; x += k)
            res.append(x + " ");

        if (res.length() == 0)
            System.out.println(-1);
        else
            System.out.println(res.toString().trim());
    }
}
