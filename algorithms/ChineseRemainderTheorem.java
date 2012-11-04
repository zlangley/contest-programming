import java.util.*;
import java.math.BigInteger;

public class CRT {
	public static BigInteger crt(BigInteger[] a, BigInteger[] n) {
		BigInteger p = BigInteger.ONE;
		for (int i = 0; i < n.length; i++) { p = p.multiply(n[i]); }
		BigInteger res = BigInteger.ZERO;
		for (int i = 0; i < n.length; i++) {
			BigInteger f = p.divide(n[i]);
			BigInteger v = f.modInverse(n[i]);
			res = res.add(a[i].multiply(f).multiply(v)).mod(p);
		}
		return res.mod(p);
	}
}
