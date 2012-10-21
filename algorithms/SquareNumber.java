public class SquareNumber {
    // newton's method to find root of x^2 = n
    // does not work for very large n due to precision of double
    public static boolean isSquare(long n) {
        double x = n/2.0;
        double lastX;
        do {
            double temp = x;
            x = (x + n/x)/2;
            lastX = temp;
        } while (Math.abs(x - lastX) > .5);

        long sqrt = (int)x;
        return sqrt*sqrt == n;
    }
}
