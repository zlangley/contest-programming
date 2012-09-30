import java.util.Arrays;
import java.util.Scanner;

public class Problem8 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();

        double[] xs = new double[n];
        double[] ys = new double[n];
        double[] rs = new double[n];
        double[] dists = new double[n];

        for (int i = 0; i < n; i++) {
            int x = scan.nextInt();
            int y = scan.nextInt();
            int r = scan.nextInt();

            dists[i] = distance(0, 0, x, y);

            xs[i] = x/dists[i];
            ys[i] = y/dists[i];
            rs[i] = r/dists[i];
        }

        boolean[] visible = new boolean[n];
        Arrays.fill(visible, true);

        final double EPS = 1e-3;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (distance(xs[i], ys[i], xs[j], ys[j]) <= rs[i] + rs[j] + EPS) {
                    if (dists[i] > dists[j])
                        visible[i] = false;
                    else if (dists[j] > dists[i])
                        visible[j] = false;
                }
            }
            System.out.println(visible[i] ? "YES" : "NO");
        }
    }

    public static double angleDifference(double theta1, double theta2) {
        double diff = theta2 - theta1;
        while (diff < -Math.PI)
            diff += 2*Math.PI;
        while (diff > Math.PI)
            diff -= 2*Math.PI;
        return diff;
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));
    }
}
