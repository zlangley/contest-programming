import java.util.HashSet;
import java.util.Scanner;

public class Office {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int rows = scan.nextInt();
        int cols = scan.nextInt();
        char color = scan.next().charAt(0);

        char[][] office = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            office[i] = scan.next().toCharArray();
        }

        HashSet<Character> neighbors = new HashSet<Character>();
        neighbors.add('.');
        neighbors.add(color);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (office[i][j] == color) {
                    if (i > 0)
                        neighbors.add(office[i-1][j]);
                    if (i < rows-1)
                        neighbors.add(office[i+1][j]);
                    if (j > 0)
                        neighbors.add(office[i][j-1]);
                    if (j < cols-1)
                        neighbors.add(office[i][j+1]);
                }
            }
        }
        System.out.println(neighbors.size() - 2);
    }
}
