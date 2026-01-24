package yandex_handbook.basic_data_structures.the_maximum_product_task;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.PriorityQueue;

public class TheMaximumProductOfFourNumbers {

    private static final FastScanner FS = new FastScanner(System.in);

    public static void main(String[] args) throws IOException {
        int n = FS.nextInt();

        PriorityQueue<Integer> maxValues = new PriorityQueue<>();
        PriorityQueue<Integer> minValues = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < n; i++) {
            int a = FS.nextInt();

            addToMaxValues(maxValues, a);
            addToMinValues(minValues, a);
        }

        long forth = maxValues.poll();
        long third = maxValues.poll();
        long second = maxValues.poll();
        long first = maxValues.poll();

        long forthFromEnd = minValues.poll();
        long thirdFromEnd = minValues.poll();
        long secondFromEnd = minValues.poll();
        long firstFromEnd = minValues.poll();

        long firstProduct = first * second * third * forth;
        long secondProduct = firstFromEnd * secondFromEnd * thirdFromEnd * forthFromEnd;
        long thirdProduct = first * second * firstFromEnd * secondFromEnd;

        long max = Math.max(firstProduct, secondProduct);
        max = Math.max(max, thirdProduct);

        System.out.print(max);
    }

    private static void addToMinValues(PriorityQueue<Integer> minValues, int a) {
        if (minValues.size() < 4) {
            minValues.add(a);

        } else if (a < minValues.peek()) {
            minValues.poll();
            minValues.add(a);
        }
    }

    private static void addToMaxValues(PriorityQueue<Integer> maxValues, int a) {
        if (maxValues.size() < 4) {
            maxValues.add(a);

        } else if (a > maxValues.peek()) {
            maxValues.poll();
            maxValues.add(a);
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
