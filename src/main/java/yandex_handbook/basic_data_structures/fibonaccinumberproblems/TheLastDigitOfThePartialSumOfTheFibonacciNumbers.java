package yandex_handbook.basic_data_structures.fibonaccinumberproblems;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class TheLastDigitOfThePartialSumOfTheFibonacciNumbers {

    private static final FastScanner FS = new FastScanner(System.in);
    private final static Map<Integer, int[]> FIBONACCI = new HashMap<>();

    public static void main(String[] args) throws IOException {
        long m = FS.nextLong();
        long n = FS.nextLong();

        int mRemainder = (int) ((m - 1L) % 60L);
        int nRemainder = (int) (n % 60L);

        int mResulst = calculateLastDigit(Math.max(mRemainder, 0))[1];
        int nResulst = calculateLastDigit(nRemainder)[1];

        System.out.print((((nResulst + 10) - mResulst) % 10));
    }

    private static int[] calculateLastDigit(int n) {
        if (n <= 1) {
            return new int[]{n, n};
        }

        if (FIBONACCI.containsKey(n)) {
            return FIBONACCI.get(n);

        } else {
            int[] first = calculateLastDigit(n - 1);
            FIBONACCI.put(n - 1, first);

            int[] second = calculateLastDigit(n - 2);
            FIBONACCI.put(n - 2, second);

            int fibonacci = (first[0] + second[0]) % 10;
            int sum = (fibonacci + first[1]) % 10;

            return new int[]{fibonacci, sum};
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