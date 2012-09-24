import java.util.Scanner;

public class I {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		for (int i = 0; i < n; i++) {
			Complex z1 = new Complex(scan.nextDouble(), scan.nextDouble());
			Complex z2 = new Complex(scan.nextDouble(), scan.nextDouble());
			Complex z3 = new Complex(scan.nextDouble(), scan.nextDouble());

			Complex g = z1.plus(z2.plus(z3)).divide(3);
			Complex z = z1.times(z2).plus(z1.times(z3)).plus(z2.times(z3)).divide(3).negative();

			Complex ans = g.plus(g.times(g).plus(z).sqrt());
			Complex ans2 = g.plus(g.times(g).plus(z).sqrt().negative());

			if (ans.a > ans2.a) {
				Complex temp = ans;
				ans = ans2;
				ans2 = temp;
			} else if (ans.a == ans2.a && ans.b > ans2.b) {
				Complex temp = ans;
				ans = ans2;
				ans2 = temp;
			}

			double x1 = (z1.a + z2.a)/2;
			double y1 = (z1.b + z2.b)/2;

			double dist = Math.sqrt((x1 - ans.a)*(x1 - ans.a) + (y1 - ans.b)*(y1 - ans.b)) +
				Math.sqrt((x1 - ans2.a)*(x1 - ans2.a) + (y1 - ans2.b)*(y1 - ans2.b));

			System.out.printf("%d %.2f %.2f %.2f %.2f %.2f\n", i + 1, ans.a, ans.b, ans2.a, ans2.b,
					dist);
		}
	}
}


class Complex {
	double a, b;

	public Complex(double a, double b) {
		this.a = a;
		this.b = b;
	}

	public Complex plus(Complex z) {
		return new Complex(a + z.a, b + z.b);
	}

	public Complex times(Complex z) {
		return new Complex(a*z.a - b*z.b, a*z.b + b*z.a);
	}

	public Complex divide(double r) {
		return new Complex(a/r, b/r);
	}

	public Complex negative() {
		return new Complex(-a, -b);
	}

	public Complex sqrt() {
		double r = Math.sqrt(a*a + b*b);
		int sign = (b < 0 ? -1 : 1);

		return new Complex(Math.sqrt((r + a)/2), sign*Math.sqrt((r - a)/2));
	}

	@Override public String toString() {
		return a + " + " + b + "i";
	}
}
