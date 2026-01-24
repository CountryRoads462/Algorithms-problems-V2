package yandex_handbook.basic_data_structures.fibonaccinumberproblems;

import java.io.IOException;
import java.io.InputStream;

public class FibonacciNumbers {

    private static final FastScanner FS = new FastScanner(System.in);

    private static final int[] FIBONACCI = new int[46];

    public static void main(String[] args) throws IOException {
        int n = FS.nextInt();

        int result = calculateFibonacci(n);

        System.out.println(result);
    }

    private static int calculateFibonacci(int n) {
        if (n <= 1) {
            return n;

        } else {
            if (FIBONACCI[n] != 0) {
                return FIBONACCI[n];

            } else {
                FIBONACCI[n - 1] = calculateFibonacci(n - 1);
                FIBONACCI[n - 2] = calculateFibonacci(n - 2);

                return FIBONACCI[n - 1] + FIBONACCI[n - 2];
            }
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
