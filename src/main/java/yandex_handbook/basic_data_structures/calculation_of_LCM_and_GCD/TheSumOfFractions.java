package yandex_handbook.basic_data_structures.calculation_of_LCM_and_GCD;

import java.io.IOException;
import java.io.InputStream;

public class TheSumOfFractions {

    private static final FastScanner FS = new FastScanner(System.in);

    public static void main(String[] args) throws IOException {
        int a = FS.nextInt();
        int b = FS.nextInt();
        int c = FS.nextInt();
        int d = FS.nextInt();

        long gcd = calculateGcd(b, d);
        long scm = ((long) b) * ((long) d) / gcd;

        long producedA = scm / b * a;
        long producedC = scm / d * c;

        long sumNumerator = producedA + producedC;

        if (sumNumerator == scm) {
            System.out.print(1 + " " + 1);

        } else {
            long finalGcd = calculateGcd(sumNumerator, scm);

            System.out.print(sumNumerator / finalGcd + " " + scm / finalGcd);
        }
    }

    private static long calculateGcd(long b, long d) {
        if (b == 0 || d == 0) {
            return Math.max(b, d);
        } else {
            return calculateGcd(d, b % d);
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
