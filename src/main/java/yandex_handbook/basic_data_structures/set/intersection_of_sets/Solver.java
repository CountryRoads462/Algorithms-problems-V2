package yandex_handbook.basic_data_structures.set.intersection_of_sets;

import java.io.IOException;
import java.io.InputStream;

public class Solver {

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);

        int[] arr = new int[1_000_001];
        int count = 0;

        int numberOfSets = fs.nextInt();
        for (int i = 0; i < numberOfSets; i++) {
            int numberOfElems = fs.nextInt();
            for (int j = 0; j < numberOfElems; j++) {
                int x = fs.nextInt();
                arr[x]++;
                if (arr[x] == numberOfSets) {
                    count++;
                }
            }
        }

        System.out.println(count);
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
    }
}
