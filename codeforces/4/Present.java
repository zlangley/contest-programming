import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Present {
    static class Envelope implements Comparable<Envelope> {
        int width, height, id;

        public int compareTo(Envelope e) {
            if (e.width == width)
                return height - e.height;
            return width - e.width;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int n = scan.nextInt();
        int w = scan.nextInt();
        int h = scan.nextInt();

        ArrayList<Envelope> envs = new ArrayList<Envelope>();
        for (int i = 0; i < n; i++) {
            Envelope e = new Envelope();
            e.width = scan.nextInt();
            e.height = scan.nextInt();
            e.id = i + 1;
            if (e.width > w && e.height > h)
                envs.add(e);
        }

        if (envs.size() == 0) {
            System.out.println("0");
            return;
        }

        Collections.sort(envs);

        int max = 1, maxIndex = 0;
        int[] lis = new int[envs.size()];
        Arrays.fill(lis, 1);
        for (int i = 1; i < envs.size(); i++) {
            for (int j = 0; j < i; j++) {
                if (envs.get(i).width != envs.get(j).width && envs.get(j).height < envs.get(i).height) {
                    lis[i] = Math.max(lis[i], lis[j] + 1);
                    if (max < lis[i]) {
                        max = lis[i];
                        maxIndex = i;
                    }
                }
            }
        }

        System.out.println(max);

        StringBuilder res = new StringBuilder("" + envs.get(maxIndex).id);
        for (int i = maxIndex - 1; i >= 0; i--) {
            if (lis[i] == max - 1 && envs.get(i).width != envs.get(maxIndex).width 
                                  && envs.get(i).height < envs.get(maxIndex).height) {
                res.insert(0, envs.get(i).id + " ");
                maxIndex = i;
                max--;
            }
        }
        System.out.println(res);
    }
}
