import java.util.ArrayList;
import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int p = scan.nextInt();
        for (int i = 0; i < p; i++) {
            int line = scan.nextInt();
            int m = scan.nextInt();

            ArrayList<Integer> list = new ArrayList<Integer>();
            int[] medians = new int[(m + 1)/2];
            int mPos = 0;

            for (int j = 0; j < m; j++) {
                insertSorted(list, scan.nextInt());
                if (j % 2 == 0)
                    medians[mPos++] = list.get(list.size() / 2);
            }

            System.out.println(line + " " + medians.length);
            for (int j = 0; j < medians.length; j++) {
                System.out.print(medians[j]);
                if ((j + 1) % 10 == 0)
                    System.out.println();
                else
                    System.out.print(" ");
            }
            if (medians.length % 10 != 0)
                System.out.println();
        }
    }

    public static void insertSorted(ArrayList<Integer> list, int num) {
        if (list.size() == 0) {
            list.add(num);
            return;
        }
        
        int i;
        for (i = 0; i < list.size() && num < list.get(i); i++);

        list.add(i, num);
    }
}
