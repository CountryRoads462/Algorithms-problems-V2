package yandex_handbook.basic_data_structures.set.how_many_different_ors;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class Solver {

    private static final FastScanner FS = new FastScanner(System.in);

    public static void main(String[] args) throws Exception {
        int numberOfElems = FS.nextInt();

        Set<Integer> numberOfOrs = new HashSet<>();

        Set<Integer> prev = new HashSet<>();
        Set<Integer> curr;

        for (int i = 0; i < numberOfElems; i++) {
            curr =  new HashSet<>();
            int userInput = FS.nextInt();

            curr.add(userInput);

            for (int prevNum : prev) {
                curr.add(userInput | prevNum);
            }

            numberOfOrs.addAll(curr);
            prev = new HashSet<>(curr);
        }

        System.out.println(numberOfOrs.size());
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
