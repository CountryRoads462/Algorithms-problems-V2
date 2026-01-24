package yandex_handbook.basic_data_structures.the_sum_of_two_numbers;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

public class APlusBBinary {

    private static final FastScanner FS = new FastScanner(System.in);

    public static void main(String[] args) throws IOException {
        String[] first = FS.nextLine().split("");
        String[] second = FS.nextLine().split("");

        LinkedList<String> list = new LinkedList<>();

        int overflow = 0;

        for (int i = first.length - 1,
             j = second.length - 1;
             i >= 0 || j >= 0;
             i--, j--) {
            int firstNum = i >= 0 ? Integer.parseInt(first[i]) : 0;
            int secondNum = j >= 0 ? Integer.parseInt(second[j]) : 0;

            int sum = firstNum + secondNum +  overflow;

            if (sum >= 2) {
                overflow = 1;
                list.addFirst(sum == 2 ? "0" : "1");

            } else {
                overflow = 0;
                list.addFirst(String.valueOf(sum));
            }
        }

        if (overflow == 1) {
            list.addFirst("1");
        }

        System.out.print(String.join("", list));
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
