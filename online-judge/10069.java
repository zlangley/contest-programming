import java.util.Scanner;
import java.math.BigInteger;

// Distinct Subsequences.
class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int n = scan.nextInt();
        for (int a = 0; a < n; a++) {
            String sequence = scan.next();
            String subsequence = scan.next();
            
            // m[i][j] = the number of occurences of subsequence[0..i] in sequence[0..j]
            BigInteger[][] m = new BigInteger[subsequence.length()][sequence.length()];

            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m[i].length; j++) {
                    if (i > j)
                        m[i][j] = BigInteger.ZERO;
                    else if (sequence.charAt(j) == subsequence.charAt(i)) {
                        if (j == 0)
                            m[i][j] = BigInteger.ONE;
                        else if (i == 0)
                            m[i][j] = m[i][j - 1].add(BigInteger.ONE);
                        else
                            m[i][j] = m[i][j - 1].add(m[i - 1][j - 1]);
                    } else {
                        if (j == 0)
                            m[i][j] = BigInteger.ZERO;
                        else
                            m[i][j] = m[i][j - 1];
                    }
                }
            }

            System.out.println(m[subsequence.length() - 1][sequence.length() - 1]);
        }
    }
}
