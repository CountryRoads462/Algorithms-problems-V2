package yandex_handbook.basic_data_structures.graph_representation_in_computer_memory;

import java.io.IOException;
import java.io.InputStream;
import java.util.StringJoiner;

public class FriendlyGraph {

    private static final FastScanner FS = new FastScanner(System.in);
    private static int maxPersonNumber = 0;

    public static void main(String[] args) throws IOException {
        int g = FS.nextInt();

        int[][] groups = new int[g][];

        for (int i = 0; i < g; i++) {
            int[] group = enterGroup();
            groups[i] = group;
        }

        boolean[][] matrix = new boolean[maxPersonNumber][maxPersonNumber];

        for (int i = 0; i < g; i++) {
            proceedGroup(groups[i], matrix);
        }

        System.out.println(maxPersonNumber);
        printMatrix(matrix);
    }

    private static void printMatrix(boolean[][] matrix) {
        StringJoiner rows = new StringJoiner("\n");
        for (int i = 0; i < matrix.length; i++) {
            StringJoiner columns = new StringJoiner(" ");
            for (int j = 0; j < matrix[0].length; j++) {
                columns.add(matrix[i][j] ? "1" : "0");
            }
            rows.add(columns.toString());
        }

        System.out.println(rows.toString());
    }

    private static void proceedGroup(int[] group, boolean[][] matrix) {
        for (int i = 1; i < group.length; i++) {
            for (int j = 0; j < i; j++) {
                matrix[group[i] - 1][group[j] - 1] = true;
                matrix[group[j] - 1][group[i] - 1] = true;
            }
        }
    }

    private static int[] enterGroup() throws IOException {
        int k = FS.nextInt();
        int[] group = new int[k];
        for (int i = 0; i < k; i++) {
            int personNumber = FS.nextInt();
            checkOnMax(personNumber);
            group[i] = personNumber;
        }
        return group;
    }

    private static void checkOnMax(int personNumber) {
        if (personNumber > maxPersonNumber) {
            maxPersonNumber = personNumber;
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

        char nextChar() throws IOException {
            int c;
            while ((c = read()) <= ' ') {
                if (c == -1) return (char) -1;
            }
            return (char) c;
        }
    }
}
