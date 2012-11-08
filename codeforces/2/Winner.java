import java.util.LinkedList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class Winner {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int[] score = new int[n];
        String[] names = new String[n];
        HashMap<String, Integer> scoreMap = new HashMap<String, Integer>();

        for (int i = 0; i < n; i++) {
            names[i] = scan.next();
            score[i] = scan.nextInt();

            int old = (scoreMap.containsKey(names[i]) ? scoreMap.get(names[i]) : 0);
            scoreMap.put(names[i], old + score[i]);
        }

        int maxScore = scoreMap.get(names[0]);
        for (int s : scoreMap.values())
            maxScore = Math.max(s, maxScore);

        HashMap<String, Integer> winnerMap = new HashMap<String, Integer>();
        for (int i = 0; i < n; i++) {
            if (scoreMap.get(names[i]) != maxScore)
                continue;

            int old = (winnerMap.containsKey(names[i]) ? winnerMap.get(names[i]) : 0);
            if (old + score[i] >= maxScore) {
                System.out.println(names[i]);
                break;
            }
            winnerMap.put(names[i], old + score[i]);
        }
    }
}
