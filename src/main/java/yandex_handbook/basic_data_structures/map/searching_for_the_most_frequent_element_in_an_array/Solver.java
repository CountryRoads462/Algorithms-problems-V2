package yandex_handbook.basic_data_structures.map.searching_for_the_most_frequent_element_in_an_array;

import java.io.IOException;
import java.io.InputStream;

public class Solver {

    private static final FastScanner FS = new FastScanner(System.in);

    public static void main(String[] args) throws Exception {
        int numberOfElements = FS.nextInt();

        int frequentNumber = 0;

        int[] map = new int[100_001];

        for (int i = 0; i < numberOfElements; i++) {
            int userInputNumber = FS.nextInt();
            int incrementedCount = ++map[userInputNumber];
            if (incrementedCount > map[frequentNumber]) {
                frequentNumber = userInputNumber;
            } else if (incrementedCount == map[frequentNumber] && userInputNumber < frequentNumber) {
                frequentNumber = userInputNumber;
            }
        }

        System.out.println(frequentNumber);
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
