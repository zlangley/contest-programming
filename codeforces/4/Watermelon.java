import java.util.Scanner;

public class Watermelon {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        System.out.println((n != 2 && n % 2 == 0) ? "YES" : "NO");
    }
}
