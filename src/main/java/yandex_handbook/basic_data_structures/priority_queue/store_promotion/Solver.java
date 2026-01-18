package yandex_handbook.basic_data_structures.priority_queue.store_promotion;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class Solver {

    private static final FastScanner FS = new FastScanner(System.in);

    public static void main(String[] args) throws IOException {
        int n = FS.nextInt();
        int k = FS.nextInt();

        CustomPriorityQueue priorityQueue = new CustomPriorityQueue(n);

        long sum = 0;

        for (int i = 0; i < n; i++) {
            int a = FS.nextInt();

            if (a < 0) {
                continue;
            }
            if (priorityQueue.size == k) {
                if (a > priorityQueue.arr[0]) {
                    sum += a;
                    sum -= priorityQueue.arr[0];
                    priorityQueue.increaseRoot(a);
                }

            } else {
                priorityQueue.add(a);
                sum += a;
            }
        }

        System.out.print(sum);
    }

    private static class CustomPriorityQueue {

        long[] arr;

        int size;

        CustomPriorityQueue(int n) {
            arr = new long[n];
        }

        void increaseRoot(long a) {
            arr[0] = a;
            shiftDown(0);
        }

        private void shiftDown(int index) {
            int smallest = index;

            int left = left(index);
            if (left < size && arr[left] < arr[smallest]) {
                smallest = left;
            }

            int right = right(index);
            if (right < size && arr[right] < arr[smallest]) {
                smallest = right;
            }

            if (index != smallest) {
                swap(index, smallest);
                shiftDown(smallest);
            }
        }

        private int left(int index) {
            return 2 * index + 1;
        }

        private int right(int index) {
            return 2 * index + 2;
        }

        public void add(long a) {
            arr[size] = a;
            shiftUp(size);
            size++;
        }

        private void shiftUp(int index) {
            int parent = parent(index);
            if (index != 0 && arr[index] < arr[parent]) {
                swap(index, parent);
                shiftUp(parent);
            }
        }

        private int parent(int index) {
            return (index - 1) / 2;
        }

        private void swap(int a, int b) {
            long temp = arr[a];
            arr[a] = arr[b];
            arr[b] = temp;
        }

        @Override
        public String toString() {
            return Arrays.toString(arr);
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
