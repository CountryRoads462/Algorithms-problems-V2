package yandex_handbook.basic_data_structures.map.performing_map_operations;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Solver {

    private static final FastScanner FS = new FastScanner(System.in);
    private static final Map<Integer, Integer> MAP = new HashMap<>();

    public static void main(String[] args) throws Exception {
        int numberOfQueries = FS.nextInt();
        for (int i = 0; i < numberOfQueries; i++) {
            int queryType = FS.nextInt();
            switch (queryType) {
                case 1 -> executeFirstQuery();
                case 2 -> executeSecondQuery();
            }
        }
    }

    private static void executeSecondQuery() throws Exception {
        int key = FS.nextInt();
        int value = MAP.getOrDefault(key, -1);
        System.out.println(value);
    }

    private static void executeFirstQuery() throws Exception {
        int key = FS.nextInt();
        int value = FS.nextInt();
        MAP.put(key, value);
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
