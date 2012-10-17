import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // divide by 5 to make table smaller
        int[] denoms = { 1, 2, 4, 10, 20, 40, 100, 200, 400, 1000, 2000 };
        long[][] dp = new long[6001][denoms.length+1];
        Arrays.fill(dp[0], 1);
        for (int i = 0; i < dp.length; i++)
            dp[i][1] = 1;

        for (int i = 1; i <= 6000; i++) {
            for (int j = 2; j <= denoms.length; j++) {
                dp[i][j] = dp[i][j-1];
                if (i >= denoms[j-1])
                    dp[i][j] += dp[i-denoms[j-1]][j];
            }
        }

        while (scan.hasNext()) {
            String s = scan.next();
            int dot = s.indexOf('.');
            int cents = (Integer.parseInt(s.substring(0, dot)) * 100 +
                Integer.parseInt(s.substring(dot+1))) / 5;

            if (cents == 0)
                return;

           System.out.printf("%6s%17d\n", s, dp[cents][denoms.length]);
        }
    }
}
