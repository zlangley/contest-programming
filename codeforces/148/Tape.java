import java.util.ArrayList;
import java.util.Scanner;

public class Tape {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int q = scan.nextInt();

        String s = scan.next();

        for (int i = 0; i < q; i++) {
            int l = scan.nextInt();
            int r = scan.nextInt();
            int[] count = new int[10];

            char[] subTape = s.substring(l-1, r).toCharArray();
            boolean[] deleted = new boolean[subTape.length];

            int dir = 1;
            int head = 0;

            int prevHead = 0;
            char prevChar = '\0';
            while (head >= 0 && head < subTape.length) {
                char c = subTape[head];

                if (c == '<') {
                    dir = -1;
                    if (prevChar == '>' || prevChar == '<')
                        deleted[prevHead] = true;
                } else if (c == '>') {
                    dir = 1;
                    if (prevChar == '>' || prevChar == '<')
                        deleted[prevHead] = true;
                } else {
                    count[c - '0']++;
                    subTape[head] = (char)(c - 1);
                    deleted[head] = (c == '0');
                }
                prevHead = head;
                prevChar = c;
                do {
                    head += dir;
                } while (head >= 0 && head < subTape.length && deleted[head]);
            }

            StringBuilder res = new StringBuilder();
            for (int c : count)
                res.append(c + " ");
            System.out.println(res.toString().trim());
        }
    }
}
