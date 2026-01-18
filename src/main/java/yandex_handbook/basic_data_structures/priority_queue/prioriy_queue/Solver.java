package yandex_handbook.basic_data_structures.priority_queue.prioriy_queue;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.PriorityQueue;

public class Solver {

    private static final FastScanner FS = new FastScanner(System.in);
    private static final PriorityQueue<Integer> PRIORITY_QUEUE = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) throws IOException {
        int numberOfQueries = FS.nextInt();

        for (int i = 0; i < numberOfQueries; i++) {
            int queryType = FS.nextInt();

            switch (queryType) {
                case 1 -> handleFirst();
                case 2 -> handleSecond();
            }

            if (!PRIORITY_QUEUE.isEmpty()) {
                System.out.println(PRIORITY_QUEUE.peek());
            } else {
                System.out.println(-1);
            }
        }
    }

    private static void handleSecond() {
        if (!PRIORITY_QUEUE.isEmpty()) {
            PRIORITY_QUEUE.poll();
        }
    }

    private static void handleFirst() throws IOException {
        int newNumber = FS.nextInt();
        PRIORITY_QUEUE.add(newNumber);
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
