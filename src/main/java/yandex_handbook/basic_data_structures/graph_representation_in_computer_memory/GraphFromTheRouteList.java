package yandex_handbook.basic_data_structures.graph_representation_in_computer_memory;

import java.io.IOException;
import java.io.InputStream;
import java.util.StringJoiner;

public class GraphFromTheRouteList {

    private static final FastScanner FS = new FastScanner(System.in);

    private static Integer prevStop;
    private static boolean[][] firstGraph;
    private static boolean[][] secondGraph;
    private static int[] currentRoute;
    private static int currentRouteSize;

    public static void main(String[] args) throws IOException {
        int n = FS.nextInt();
        int m = FS.nextInt();

        firstGraph = new boolean[n][n];
        secondGraph = new boolean[n][n];

        for (int i = 0; i < m; i++) {
            enterRoute();
        }

        printGraph(firstGraph);
        System.out.println();
        printGraph(secondGraph);
    }

    private static void printGraph(boolean[][] graph) {
        StringJoiner matrix = new StringJoiner("\n");
        for (int i = 0; i < graph.length; i++) {
            StringJoiner row = new StringJoiner(" ");
            for (int j = 0; j < graph[0].length; j++) {
                row.add(graph[i][j] ? "1" : "0");
            }
            matrix.add(row.toString());
        }
        System.out.print(matrix.toString());
    }

    private static void enterRoute() throws IOException {
        int routeLength = FS.nextInt();

        currentRoute = new int[routeLength - 1];

        for (int i = 0; i < routeLength; i++) {
            enterStop();
        }

        prevStop = null;
        currentRouteSize = 0;
    }

    private static void enterStop() throws IOException {
        int stopNumber = FS.nextInt();
        addToFirstGraph(stopNumber);
        addToSecondGraph(stopNumber);
    }

    private static void addToFirstGraph(int stopNumber) {
        if (prevStop != null) {
            connectStops(stopNumber, prevStop, firstGraph);
        }

        prevStop = stopNumber;
    }

    private static void connectStops(int firstStop, int secondStop, boolean[][] graph) {
        graph[firstStop - 1][secondStop - 1] = true;
        graph[secondStop - 1][firstStop - 1] = true;
    }

    private static void addToSecondGraph(int stopNumber) {
        if (currentRouteSize != 0) {
            for (int i = 0; i < currentRouteSize; i++) {
                connectStops(stopNumber, currentRoute[i], secondGraph);
            }
        }

        if (currentRouteSize != currentRoute.length) {
            currentRoute[currentRouteSize] = stopNumber;
            currentRouteSize++;
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
    }
}
