import java.util.Scanner;

class Main {
	static int[] primes = new int[446];
	static boolean[] isPrime = new boolean[3162];

	static {
		for (int i = 2; i < isPrime.length; i++)
			isPrime[i] = true;

		for (int i = 2; i*i < isPrime.length; i++)
			if (isPrime[i])
				for (int j = 2*i; j < isPrime.length; j += i)
					isPrime[j] = false;

		int p = 0;
		for (int i = 0; i < isPrime.length; i++)
			if (isPrime[i])
				primes[p++] = i;
	}

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		while (scan.hasNextInt()) {
			int n = scan.nextInt();

			if (n < 8)
				System.out.println("Impossible.");
			else if (n % 2 == 0) {
				int[] sp = split(n - 4);
				System.out.println("2 2 " + sp[0] + " " + sp[1]);
			} else {
				int[] sp = split(n - 5);
				System.out.println("2 3 " + sp[0] + " " + sp[1]);
			}
		}
	}

	public static int[] split(int n) {
		for (int p : primes)
			if (isPrime(n - p))
				return new int[] { p, n - p };
		return null;
	}

	public static boolean isPrime(int n) {
		if (n < isPrime.length)
			return isPrime[n];
		for (int i = 2; i <= (int)(Math.sqrt(n) + 1); i++)
			if (n % i == 0)
				return false;
		return true;
	}
}
