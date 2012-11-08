import java.util.Scanner;

public class Spreadsheets {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        while (--n >= 0) {
            String coord = scan.next();

            int cIndex = coord.indexOf('C');
            if (coord.startsWith("R") && Character.isDigit(coord.charAt(1)) && cIndex != -1) {
                int row = Integer.parseInt(coord.substring(1, cIndex));
                int col = Integer.parseInt(coord.substring(cIndex + 1));

                StringBuilder colStr = new StringBuilder();
                while (col > 0) {
                    colStr.insert(0, (char)((col - 1) % 26 + 'A'));
                    col = (col - 1) / 26;
                }
                System.out.println(colStr.toString() + row);
            } else {
                int split = 1;
                while (!Character.isDigit(coord.charAt(split)))
                    split++;

                String colStr = coord.substring(0, split);
                int row = Integer.parseInt(coord.substring(split));

                int col = 0;
                for (int i = 0; i < colStr.length(); i++) {
                    col *= 26;
                    col += colStr.charAt(i) - 'A' + 1;
                }
                System.out.println("R" + row + "C" + col);
            }
        }
    }
}
