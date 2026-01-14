package yandex_handbook.basic_data_structures.deque.a_ribbon_of_letters;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

public class Solver {

    private static final FastScanner SCANNER = new FastScanner(System.in);

    public static void main(String[] args) throws IOException {
        SCANNER.nextLine();

        LinkedList<Character> letters = new LinkedList<>();
        for (char ch : SCANNER.nextLine().toCharArray()) {
            letters.add(ch);
        }

        StringBuilder sb = new StringBuilder();

        while (!letters.isEmpty()) {
            if (letters.getLast() < letters.getFirst()) {
                sb.append(letters.pollLast());
            } else {
                sb.append(letters.pollFirst());
            }
        }

        System.out.println(sb);
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
