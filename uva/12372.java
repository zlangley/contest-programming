import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            boolean good = true;
            for (int j = 0; j < 3; j++)
                if (scan.nextInt() > 20)
                    good = false;
            System.out.println("Case " + (i + 1) + ": " + (good ? "good" : "bad"));
        }
    }
}
