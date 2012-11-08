import java.util.Arrays;
import java.util.Scanner;

public class Triangle {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int[] sides = new int[4];
        for (int i = 0; i < sides.length; i++)
            sides[i] = scan.nextInt();

        Arrays.sort(sides);

        if (sides[0] + sides[1] > sides[2] || sides[1] + sides[2] > sides[3])
            System.out.println("TRIANGLE");
        else if (sides[0] + sides[1] == sides[2] || sides[1] + sides[2] == sides[3])
            System.out.println("SEGMENT");
        else
            System.out.println("IMPOSSIBLE");
    }
}
