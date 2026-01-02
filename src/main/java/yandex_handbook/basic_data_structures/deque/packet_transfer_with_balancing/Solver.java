package yandex_handbook.basic_data_structures.deque.packet_transfer_with_balancing;

import java.io.IOException;
import java.io.InputStream;

public class Solver {

    private static final FastScanner SCANNER = new FastScanner(System.in);

    public static void main(String[] args) throws IOException {
        int numberOfPackages = SCANNER.nextInt();
        int numberOfServers = SCANNER.nextInt();

        String[] result = new String[numberOfPackages];

        ServerCluster serverCluster = new ServerCluster(numberOfServers);

        for (int i = 0; i < numberOfPackages; i++) {
            int arrivedAt = SCANNER.nextInt();
            int duration = SCANNER.nextInt();

            int finishedAt = serverCluster.process(arrivedAt, duration);
            result[i] = String.valueOf(finishedAt);
        }

        System.out.print(String.join(" ", result));
    }

    static class ServerCluster {

        private final int[][] servers;

        ServerCluster(int numberOfServers) {
            servers = new int[numberOfServers][2];
            for (int i = 0; i < numberOfServers; i++) {
                servers[i][0] = i;
            }
        }

        public int process(int arrivedAt, int duration) {
            int availableAt = servers[0][1];
            if (arrivedAt >= availableAt) {
                servers[0][1] = arrivedAt + duration;
            } else {
                servers[0][1] = availableAt + duration;
            }

            int finishedAt = servers[0][1];

            heapify(0);

            return finishedAt;
        }

        private void heapify(int key) {
            int left = 2 * key + 1;
            int right = 2 * key + 2;

            int smallest = key;
            if (left < servers.length && compareTwoServers(left, smallest) < 0) {
                smallest = left;
            }

            if (right < servers.length && compareTwoServers(right, smallest) < 0) {
                smallest = right;
            }

            if (smallest != key) {
                swap(key, smallest);
                heapify(smallest);
            }
        }

        private int compareTwoServers(int first, int second) {
            int compared = Integer.compare(servers[first][1], servers[second][1]);
            if (compared == 0) {
                return Integer.compare(servers[first][0], servers[second][0]);

            } else {
                return compared;
            }
        }

        private void swap(int first, int second) {
            int tempServerNumber = servers[first][0];
            int tempAvailableAt = servers[first][1];

            servers[first][0] = servers[second][0];
            servers[first][1] = servers[second][1];

            servers[second][0] = tempServerNumber;
            servers[second][1] = tempAvailableAt;
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
