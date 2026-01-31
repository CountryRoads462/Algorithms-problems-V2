package yandex_handbook.basic_data_structures.calculation_of_LCM_and_GCD;

import java.io.IOException;
import java.io.InputStream;

public class TheDiophantineEquation {

    private static final FastScanner FS = new FastScanner(System.in);

    public static void main(String[] args) throws IOException {
        int a = FS.nextInt();
        int b = FS.nextInt();
        int c = FS.nextInt();

        int[][][] f = new int[a][b][c];

        int gcd = calculateGcd(Math.abs(a), Math.abs(b));

        System.out.print(Math.abs(c) % gcd == 0 ? "YES" : "NO");
    }

    private static int calculateGcd(int a, int b) {
        if (a == 0 || b == 0) {
            return Math.max(a, b);

        } else {
            return calculateGcd(b, a % b);
        }
    }

    private static boolean process(int a, int b, int c) {
        if (a == c || b == c) {
            return true;

        } else if (a == 0 || b == 0) {
            return false;

        } else {
            return process(b, a % b, c);
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

        long nextLong() throws IOException {
            int c;
            while ((c = read()) <= ' ') {
                if (c == -1) return -1;
            }
            int sign = 1;
            if (c == '-') {
                sign = -1;
                c = read();
            }
            long val = c - '0';
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
