import java.util.*;
import java.math.BigInteger;

// chinese remainder theorem
// solves for x where:
// x \equiv a_1 (mod n_1)
// x \equiv a_2 (mod n_2)
// ...

public class CRT {
	// a is a list of modular residues
	// n is a list of the respective moduli
	// a[i] and n[i] must be co-prime (for the multiplicative inverse)
	// all n's should be co-prime for the smallest result (if not, 
	//   the p on the return line should be replaced with lcm(n[0]...n[i])
	static BigInteger crt(BigInteger[] a, BigInteger[] n) {
		if (a.length != n.length) { return BigInteger.ZERO; }	// input error
		BigInteger p = BigInteger.ONE;			// product of all moduli
		for (int i = 0; i < n.length; i++) { p = p.multiply(n[i]); }
		BigInteger x = BigInteger.ZERO;			// the solution
		for (int i = 0; i < n.length; i++) {
			BigInteger f = p.divide(n[i]);
			BigInteger v = f.modInverse(n[i]);
			x = x.add(a[i].multiply(f).multiply(v));
		}
		return x.mod(p);
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("x = a[i] (mod n[i])");
		System.out.println("Enter pairs of: a n");
		ArrayList<BigInteger> a = new ArrayList<BigInteger>();
		ArrayList<BigInteger> n = new ArrayList<BigInteger>();
		while (scan.hasNext()) {
			a.add(new BigInteger(scan.next()));
			n.add(new BigInteger(scan.next()));
		}
		BigInteger x = crt(	a.toArray(new BigInteger[a.size()]), 
							n.toArray(new BigInteger[n.size()]) );
		System.out.println("x: " + x);
	}
}
