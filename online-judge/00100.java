import java.util.Scanner;

// The 3n + 1 problem.
class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        table[1] = 1;

        while (scan.hasNextInt()) {
            int a = scan.nextInt();
            int b = scan.nextInt();

            int max = 0;
            for (int i = a; i <= b; i++)
                max = Math.max(max, cycleLength(i));
            for (int i = b; i <= a; i++)
                max = Math.max(max, cycleLength(i));
            System.out.println(a + " " + b + " " + max);
        }
    }

    static int[] table = new int[1000000];
    public static int cycleLength(long n) {
        if (n < table.length && table[(int)n] != 0)
            return table[(int)n];

        int answer = 0;
        if (n % 2 == 1)
            answer = cycleLength(3*n + 1) + 1;
        else
            answer = cycleLength(n/2) + 1;

        if (n < table.length)
            table[(int)n] = answer;

        return answer;
    }
}
