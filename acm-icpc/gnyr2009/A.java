import java.util.Arrays;
import java.util.Scanner;

public class A {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		for (int i = 0; i < n; i++) {
			int line = scan.nextInt();
			int[] arr = new int[10];

			for (int j = 0; j < arr.length; j++)
				arr[j] = scan.nextInt();

			Arrays.sort(arr);

			System.out.println(line + " " + arr[7]);
		}
	}
}
