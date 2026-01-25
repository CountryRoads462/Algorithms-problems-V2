package yandex_handbook.basic_data_structures.fibonaccinumberproblems;

import java.io.IOException;
import java.io.InputStream;

public class TheHugeFibonacciNumber {

    private static final FastScanner FS = new FastScanner(System.in);
    private static final Long[] FIBONACCI = new Long[50_000_000];

    public static void main(String[] args) throws IOException {
        long n = FS.nextLong();
        long m = FS.nextLong();

        long pisanoPeriod = calculatePisanoPeriod(m);

        int remainder = (int) (n % pisanoPeriod);

        long result = calculateRemainderOfFibonacciNumber(remainder, m);

        System.out.print(result);
    }

    private static long calculateRemainderOfFibonacciNumber(int n, long m) {
        if (n <= 1) {
            return n;
        }

        if (FIBONACCI[n] != null) {
            return FIBONACCI[n];

        } else {
            FIBONACCI[n - 1] = calculateRemainderOfFibonacciNumber(n - 1, m);
            FIBONACCI[n - 2] = calculateRemainderOfFibonacciNumber(n - 2, m);

            return (FIBONACCI[n - 1] + FIBONACCI[n - 2]) % m;
        }
    }

    private static int calculatePisanoPeriod(long m) {
        long current = 0;
        long next = 1;
        int period = 0;
        while (true) {
            long oldNext = next;
            next = (current + next) % m;
            current = oldNext;
            period = period + 1;
            if (current == 0 && next == 1) {
                return period;
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
