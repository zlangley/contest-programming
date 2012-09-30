import java.util.Scanner;

class H {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        long[] small = new long[101];
        small[2] = 1;
        small[3] = 7;
        small[4] = 4;
        small[5] = 2;
        small[6] = 6; // only for recursion
        small[7] = 8;
        small[8] = 10;

        for (int i = 9; i < small.length; i++) {
            small[i] = Long.parseLong(small[2] + "" + small[i - 2]);
            for (int j = 3; j <= i; j++) {
                String next = small[j] + "" + (i - j == 6 ? 0 : small[i - j]);
                small[i] = Math.min(Long.parseLong(next), small[i]);
            }
        }

        String[] large = new String[101];
        large[2] = "1";
        large[3] = "7";

        for (int i = 4; i < large.length; i++)
            large[i] = large[i - 2] + "1";


        int n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            int num = scan.nextInt();
            System.out.println(small[num] + " " + large[num]);
        }
    }
}
