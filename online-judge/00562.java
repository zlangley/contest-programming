import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        
        for (int k = 0; k < N; k++) {
            int m = scan.nextInt();
            int[] nums = new int[m];

            int sum = 0;
            for (int i = 0; i < nums.length; i++) {
                nums[i] = scan.nextInt();
                sum += nums[i];
            }

            int[][] dp = new int[m+1][sum+1];
            for (int i = 1; i <= m; i++) {
                for (int j = 0; j <= sum; j++) {
                    if (j >= nums[i-1]) {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-nums[i-1]] + nums[i-1]);
                    } else {
                        dp[i][j] = dp[i-1][j];
                    }
                }
                // System.out.println(Arrays.toString(dp[i]));
            }

            int closestVal = sum;
            for (int j = 1; j <= sum; j++)
                closestVal = Math.min(closestVal, Math.abs(2*dp[m][j] - sum));
            System.out.println(closestVal);
        }
    }
}
