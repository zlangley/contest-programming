import java.util.Scanner;

// Funny Encryption Method.
class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		for (int i = 0; i < n; i++) {
			String s = scan.next();
			System.out.println(countOnesInBinary(Integer.parseInt(s)) + " " +
					countOnesInBinary(Integer.parseInt(s, 16)));
		}
	}

	public static int countOnesInBinary(int a) {
		int count = 0;
		for (int i = 1; i <= a; i <<= 1)
			if ((a & i) != 0)
				count++;

		return count;
	}
}
