import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            int amount = scan.nextInt();
            System.out.printf("%d %d QUARTER(S), %d DIME(S), %d NICKEL(S), %d PENNY(S)\n",
                    i + 1, amount/25, amount%25/10, amount%25%10/5, amount%5);
        }
    }
}
