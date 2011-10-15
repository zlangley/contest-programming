import java.util.Scanner;

// Light, More Light.
class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		while (true) {
			long n = scan.nextLong();
			if (n == 0)
				return;

			long sqrt = (long)(Math.round(Math.sqrt(n)));
			if (sqrt*sqrt == n)
				System.out.println("yes");
			else
				System.out.println("no");
		}
	}
}
