package yandex_handbook.basic_data_structures;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GetPeriodLengthOfFibonacciMode {

    private static final FastScanner FS = new FastScanner(System.in);
    private static final Long[] FIBONACCI = new Long[1000];

    public static void main(String[] args) throws IOException {
        System.out.print("Enter mode: ");
        int mode = FS.nextInt();

        List<Integer> list = new ArrayList<>();

        for (int i = 0; !listHasPeriod(list); i++) {
            long fibonacci = calculateFibonacci(i);
            int remainder = Math.toIntExact(fibonacci % mode);
            list.add(remainder);
        }

        int periodLength = list.size() / 2;
        System.out.println("Period length: " + periodLength);
    }

    private static long calculateFibonacci(int n) {
        return calculateFibonacci(2, n, 0, 1);
    }

    private static long calculateFibonacci(int i, int n, long first, long second) {
        if (n <= 1) {
            return n;
        }

        if (i == n) {
            return second + first;

        } else {
            if (FIBONACCI[n] != null) {
                return FIBONACCI[n];

            } else {
                long sum = first + second;
                long result = calculateFibonacci(i + 1, n, second, sum);
                FIBONACCI[n] = result;
                return result;
            }
        }
    }

    private static boolean listHasPeriod(List<Integer> list) {
        if (!list.isEmpty() && list.size() % 2 == 0) {
            List<Integer> firstHalf = list.subList(0, list.size() / 2);
            List<Integer> secondHalf = list.subList(list.size() / 2, list.size());

            return firstHalf.equals(secondHalf);

        } else {
            return false;
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
