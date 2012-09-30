import java.util.Scanner;

// Jolly Jumpers.
class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        while (scan.hasNextInt()) {
            int n = scan.nextInt();
            boolean[] used = new boolean[n];

            int last = scan.nextInt();
            for (int i = 1; i < n; i++) {
                int next = scan.nextInt();
                try {
                    used[Math.abs(last - next)] = true;
                } catch (IndexOutOfBoundsException e) { }
                last = next;
            }

            boolean jolly = true;
            for (int i = 1; i < n; i++) {
                if (!used[i]) {
                    System.out.println("Not jolly");
                    jolly = false;
                    break;
                }
            }
            if (jolly)
                System.out.println("Jolly");
        }
    }
}
