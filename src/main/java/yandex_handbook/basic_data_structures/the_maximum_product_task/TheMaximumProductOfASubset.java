package yandex_handbook.basic_data_structures.the_maximum_product_task;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class TheMaximumProductOfASubset {

    private static final FastScanner FS = new FastScanner(System.in);

    public static void main(String[] args) throws IOException {
        int n = FS.nextInt();

        int countOfPositiveNumbers = 0;
        Set<Integer> indexesOfNegativeNumbers = new HashSet<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[0], o1[0]));

        int zeroIndex = 0;

        for (int i = 0; i < n; i++) {
            int a = FS.nextInt();

            if (a > 0) {
                countOfPositiveNumbers++;
                System.out.print((i + 1) + " ");

            } else if (a < 0) {
                pq.add(new int[]{a, i + 1});
                indexesOfNegativeNumbers.add(i + 1);
            } else {
                zeroIndex = i + 1;
            }
        }

        if (countOfPositiveNumbers == 0 && indexesOfNegativeNumbers.size() < 2) {
            System.out.print(zeroIndex);

        } else {
            if (indexesOfNegativeNumbers.size() % 2 != 0) {
                int[] maxNegative = pq.poll();
                indexesOfNegativeNumbers.remove(maxNegative[1]);
            }

            indexesOfNegativeNumbers.forEach(i -> System.out.print(i + " "));
        }
    }

    static class FastScanner {

        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0;
        private int len = 0;

        FastScanner(InputStream is) {
            this.in = is;
        }

        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }

        int nextInt() throws IOException {
            int c;
            while ((c = read()) <= ' ') {
                if (c == -1) return -1;
            }
            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }
            int val = c - '0';
            while ((c = read()) > ' ') {
                val = val * 10 + (c - '0');
            }
            return val * sign;
        }

        String nextLine() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;

            while ((c = read()) == '\n' || c == '\r') {
            }

            if (c == -1) return null;

            do {
                sb.append((char) c);
            } while ((c = read()) != -1 && c != '\n' && c != '\r');

            return sb.toString();
        }
    }
}
