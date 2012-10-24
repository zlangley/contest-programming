import java.util.*;

// mostly stolen from:
// http://www.fmi.uni-sofia.bg/fmi/logic/vboutchkova/sources/KMPMatch_java.html

public class KMP {
	// creates and returns the partial match table
	// n: the needle
	// f: the failure function (partial match table)
	// v, i: indices used to compare the needle to itself
	static int[] kmpTbl(char[] n) {
		int f[] = new int[n.length];
		int v = 0;
		for (int i = 1; i < n.length; i++) {
			while (v > 0 && n[v] != n[i]) { v = f[v - 1]; }
			if (n[v] == n[i]) { v++; }
			f[i] = v;
		}
		return f;
	}
	
	// applies the knuth-morris-pratt string matching algorithm
	// return the match index
	// h: the haystack
	// n: the needle
	// f: the failure function
	// i: current index into the haystack
	// v: current index into the needle
	static int kmp(char[] h, char[] n) {
		int[] f = kmpTbl(n);
		int v = 0;
		if (h.length == 0) { return -1; }
		for (int i = 0; i < h.length; i++) {
			while (v > 0 && n[v] != h[i]) { v = f[v - 1]; }
			if (n[v] == h[i]) { v++; }
			if (v == n.length) { return i - n.length + 1; }
		}
		return -1;
	}
	
	// just sample usage.
	// char[] is used because it is faster for reasonably long strings
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.print("Haystack: ");
		char[] h = scan.nextLine().toCharArray();
		System.out.print("Needle: ");
		char[] n = scan.nextLine().toCharArray();
		int i = kmp(h, n);
		if (i >= 0) { System.out.println("Match at index: " + i); }
		else { System.out.println("No match found"); }
	}
}
