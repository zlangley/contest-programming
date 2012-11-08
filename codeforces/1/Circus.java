import java.util.Scanner;

public class Circus {
    static class Point {
        double x, y;

        public double distance(Point p) {
            return Math.sqrt((p.x - x)*(p.x - x) + (p.y - y)*(p.y - y));
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Point p = new Point();
        Point q = new Point();
        Point r = new Point();

        p.x = scan.nextDouble();
        p.y = scan.nextDouble();
        q.x = scan.nextDouble();
        q.y = scan.nextDouble();
        r.x = scan.nextDouble();
        r.y = scan.nextDouble();

        double a = p.distance(q);
        double b = q.distance(r);
        double c = r.distance(p);

        double s = (a + b + c)/2;
        double area = Math.sqrt(s*(s-a)*(s-b)*(s-c));
        double radius = a*b*c/4/area;

        double A = Math.acos((b*b + c*c - a*a) / (2*b*c));
        double B = Math.acos((a*a + c*c - b*b) / (2*a*c));
        double C = Math.acos((a*a + b*b - c*c) / (2*a*b));

        int n = (int)Math.round(Math.PI / gcd(gcd(A, B), C));
        System.out.printf("%.7f\n", n * radius * radius * Math.sin(2 * Math.PI / n) / 2);
    }

    final static double EPS = 1e-3;
    public static double gcd(double a, double b) {
        while (Math.abs(a) > EPS && Math.abs(b) > EPS) {
            if (a > b)
                a -= (int)(a / b) * b;
            else
                b -= (int)(b / a) * a;
        }
        return a + b;
    }
}
