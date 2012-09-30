import java.util.*;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;

class I {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        for (int k = 0; k < n; k++) {
            int inner = scan.nextInt();
            Point2D.Double[] innerPts = new Point2D.Double[inner];
            for (int i = 0; i < inner; i++) {
                int x = scan.nextInt();
                int y = scan.nextInt();
                innerPts[i] = new Point2D.Double(x, y);
            }

            int outer = scan.nextInt();
            Point2D.Double[] outerPts = new Point2D.Double[outer];
            for (int i = 0; i < outer; i++) {
                int x = scan.nextInt();
                int y = scan.nextInt();
                outerPts[i] = new Point2D.Double(x, y);
            }

            double min = Double.MAX_VALUE;
            for (int i = 0; i < inner; i++) {
                Line2D.Double innerLine = new Line2D.Double(innerPts[i], innerPts[(i + 1) % inner]);
                for (int o = 0; o < outer; o++) {
                    Line2D.Double outerLine = new Line2D.Double(outerPts[o], outerPts[(o + 1) % outer]);

                    min = Math.min(min, Math.min(innerLine.ptSegDist(outerPts[o]), 
                                outerLine.ptSegDist(innerPts[i])));
                }
            }
            System.out.println(min/2);
        }
    }
}
