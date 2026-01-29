package yandex_handbook.basic_data_structures.fibonaccinumberproblems;

import java.io.IOException;
import java.io.InputStream;

public class CanonicalEntryInTheFibonacciSystem {

    private static final FastScanner FS = new FastScanner(System.in);
    private static final Long[] FIBONACCI = new Long[50];

    public static void main(String[] args) throws IOException {
        int n = FS.nextInt();

        int i = 2;
        while (calculateFibonacci(i) <= n) {
            i++;
        }

        i--;

        int[] result = new int[i - 1];

        long sum = 0;
        int j = 0;
        while (sum != n) {
            long fibonacci = FIBONACCI[i];
            if (sum + fibonacci <= n) {
                sum += fibonacci;
                result[j] = 1;
                j += 2;
                i -= 2;

            } else {
                j++;
                i--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < result.length; k++) {
            sb.append(result[k]);
        }

        System.out.print(sb);
    }


    private static long calculateFibonacci(int n) {
        long result;

        if (n <= 1) {
            result = n;

        } else {
            long prev1 = 0;
            long prev2 = 1;

            long sum = prev1 + prev2;

            for (int i = 3; i <= n; i++) {
                prev1 = prev2;
                prev2 = sum;
                sum = prev1 + prev2;
            }

            result = sum;
        }

        FIBONACCI[n] = result;
        return result;
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
