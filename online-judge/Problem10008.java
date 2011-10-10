import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

// What's Cryptanalysis?
public class Problem10008 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		int n = scan.nextInt();
		scan.nextLine();

		int[] count = new int[26];
		for (int i = 0; i < n; i++) {
			String s = scan.nextLine();

			for (int j = 0; j < s.length(); j++) {
				char ch = Character.toUpperCase(s.charAt(j));
				if (!Character.isLetter(ch))
					continue;

				count[ch - 'A']++;
			}
		}
		TreeMap<Integer, TreeSet<Character>> map = new TreeMap<Integer, TreeSet<Character>>();
		for (int i = 0; i < count.length; i++) {
			int num = count[i];
			if (num == 0)
				continue;

			if (map.get(num) == null) {
				TreeSet<Character> set = new TreeSet<Character>();
				set.add((char)('A' + i));
				map.put(num, set);
			} else
				map.get(num).add((char)('A' + i));
		}

		for (int i : map.descendingKeySet()) {
			for (char c : map.get(i)) {
				System.out.println(c + " " + i);
			}
		}
	}
}
