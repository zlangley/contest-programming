import java.util.Scanner;

public class BeforeExam {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int d = scan.nextInt();
        int sumTime = scan.nextInt();

        int[] minTime = new int[d];
        int[] maxTime = new int[d];

        int minSumTime = 0;
        int maxSumTime = 0;
        for (int i = 0; i < d; i++) {
            minTime[i] = scan.nextInt();
            maxTime[i] = scan.nextInt();
            minSumTime += minTime[i];
            maxSumTime += maxTime[i];
        }

        if (sumTime < minSumTime || sumTime > maxSumTime) {
            System.out.println("NO");
        } else {
            for (int i = 0; i < d; i++) {
                if (minSumTime + maxTime[i] - minTime[i] < sumTime) {
                    minSumTime += maxTime[i] - minTime[i];
                    minTime[i] = maxTime[i];
                } else {
                    minTime[i] += sumTime - minSumTime;
                    break;
                }
            }
            System.out.println("YES");
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < d; i++)
                str.append(" " + minTime[i]);
            System.out.println(str.toString().trim());
        }
    }
}
