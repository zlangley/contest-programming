import java.util.Scanner;

class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		while (true) {
			int n = scan.nextInt();
			if (n == 0)
				break;

			if (!isPrime(n)) {
				boolean normal = false;
				for (int a = 2; a < n; a++)
					if (pow(a, n, n) != a) {
						System.out.println(n + " is normal.");
						normal = true;
						break;
					}
				if (!normal)
					System.out.println("The number " + n + " is a Carmichael number.");
			} else
				System.out.println(n + " is normal.");
		}
	}

	public static boolean isPrime(int n) {
		if (n < 2)
			return false;

		if (n == 2)
			return true;

		for (int i = 2; i <= (int)(Math.sqrt(n) + 1); i++)
			if (n % i == 0)
				return false;

		return true;
	}

	public static long pow(long x, long c, long n) {
		long z = 1;
		// 64 bits in a long.
		for (int i = 63; i >= 0; i--) {
			z = z*z % n;
			if ((c & (1L << i)) != 0)
				z = z*x % n;
		}
		return z;
	}
}
