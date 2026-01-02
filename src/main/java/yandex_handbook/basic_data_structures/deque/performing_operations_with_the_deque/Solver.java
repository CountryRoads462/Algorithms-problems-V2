package yandex_handbook.basic_data_structures.deque.performing_operations_with_the_deque;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solver {

    private static final FastScanner SCANNER = new FastScanner(System.in);
    private static final Deque<Integer> DEQUE = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        int numberOfQueries = SCANNER.nextInt();

        List<String> answers = new ArrayList<>();

        for (int i = 0; i < numberOfQueries; i++) {
            int queryType = SCANNER.nextInt();

            switch (queryType) {
                case 1 -> handleFirstQuery();
                case 2 -> handleSecondQuery();
                case 3 -> handleThirdQuery();
                case 4 -> handleForthQuery();
            }


            if (DEQUE.isEmpty()) {
                answers.add("-1");
            } else {
                answers.add(DEQUE.getFirst() + " " + DEQUE.getLast());
            }
        }

        System.out.println(String.join("\n", answers));
    }

    private static void handleFirstQuery() throws IOException {
        int number = SCANNER.nextInt();
        DEQUE.addFirst(number);
    }

    private static void handleSecondQuery() throws IOException {
        int number = SCANNER.nextInt();
        DEQUE.addLast(number);
    }

    private static void handleThirdQuery() {
        if (!DEQUE.isEmpty()) {
            DEQUE.removeFirst();
        }
    }

    private static void handleForthQuery() {
        if (!DEQUE.isEmpty()) {
            DEQUE.removeLast();
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
    }
}



