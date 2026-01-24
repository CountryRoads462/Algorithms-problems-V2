package yandex_handbook.basic_data_structures.the_maximum_product_task;

import java.io.IOException;
import java.io.InputStream;

public class TheMaximumProductIsACounterexample {

    private static final FastScanner FS = new FastScanner(System.in);

    public static void main(String[] args) throws IOException {
        int n = FS.nextInt();

        if (n >= 5) {
            printExample(n);

        } else {
            System.out.print("No");
        }
    }

    private static void printExample(int n) {
        String[] result = new String[n];
        result[0] = String.valueOf(n);
        for (int i = 1; i < n; i++) {
            result[i] = String.valueOf(i);
        }

        System.out.println("Yes");
        System.out.print(String.join(" ", result));
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
