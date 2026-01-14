package yandex_handbook.basic_data_structures.stack.the_sum_of_the_minima_on_the_segments;

import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.LinkedList;

public class Solver {

    private static final FastScanner SC = new FastScanner(System.in);

    public static void main(String[] args) throws IOException {
        int n = SC.nextInt();
        int k = SC.nextInt();

        Processor processor = new Processor(k);

        long sum = 0;

        for (int i = 0; i < n; i++) {
            long elem = SC.nextInt();
            long min = processor.process(elem);

            sum += min;
        }

        System.out.print(sum);
    }

    static class Processor {

        private final int k;
        private final LongValueBinaryHeap binaryHeap;
        private final LinkedList<LongValue> values;

        Processor(int k) {
            this.k = k;
            this.binaryHeap = new LongValueBinaryHeap();
            this.values = new LinkedList<>();
        }

        public long process(long elem) {
            LongValue newElem = new LongValue(elem);
            values.add(newElem);

            if (binaryHeap.size() == k) {
                LongValue firstElemInSubArr = values.removeFirst();
                int indexInHeap = firstElemInSubArr.getIndex();
                binaryHeap.changeValueOnIndex(newElem, indexInHeap);
                LongValue heapMinValue = binaryHeap.getRoot();
                return heapMinValue.getValue();

            } else if (binaryHeap.size() + 1 == k) {
                binaryHeap.add(newElem);
                LongValue heapMinValue = binaryHeap.getRoot();
                return heapMinValue.getValue();

            } else {
                binaryHeap.add(newElem);
                return 0;
            }
        }
    }

    static class LongValue implements Comparable<LongValue> {

        private final long value;
        private int index;

        public LongValue(long value) {
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public long getValue() {
            return value;
        }

        @Override
        public int compareTo(LongValue o) {
            return Long.compare(value, o.value);
        }
    }

    static class LongValueBinaryHeap {

        private final LongValue[] heap;
        private int size;
        private Comparator<LongValue> comparator;

        public LongValueBinaryHeap() {
            heap = new LongValue[400001];
            size = 0;
        }

        public LongValueBinaryHeap(Comparator<LongValue> comparator) {
            heap = new LongValue[300001];
            size = 0;
            this.comparator = comparator;
        }

        public LongValue getRoot() {
            return heap[0];
        }

        public int size() {
            return size;
        }

        public void add(LongValue newElem) {
            int index = size;
            heap[index] = newElem;
            newElem.setIndex(index);
            size++;
            shiftUp(index);
        }

        private void swap(int first, int second) {
            LongValue firstValue = heap[first];
            LongValue secondValue = heap[second];

            firstValue.setIndex(second);
            secondValue.setIndex(first);

            heap[first] = secondValue;
            heap[second] = firstValue;
        }

        private int parent(int index) {
            return (index - 1) / 2;
        }

        public void changeValueOnIndex(LongValue newElem, int index) {
            LongValue oldValue = heap[index];
            heap[index] = newElem;
            newElem.setIndex(index);

            if (compare(oldValue, newElem) == 0) {
                return;
            }

            if (compare(newElem, oldValue) < 0) {
                shiftUp(index);

            } else {
                shiftDown(index);
            }
        }

        private void shiftDown(int index) {
            int left = left(index);
            int right = right(index);

            int indexOfSmallest = index;

            if (left < size && compare(heap[left], heap[indexOfSmallest]) < 0) {
                indexOfSmallest = left;
            }

            if (right < size && compare(heap[right], heap[indexOfSmallest]) < 0) {
                indexOfSmallest = right;
            }

            if (indexOfSmallest != index) {
                swap(index, indexOfSmallest);
                shiftDown(indexOfSmallest);
            }
        }

        private void shiftUp(int index) {
            while (index != 0 && compare(heap[index], heap[parent(index)]) < 0) {
                swap(index, parent(index));
                index = parent(index);
            }
        }

        private int left(int index) {
            return index * 2 + 1;
        }

        private int right(int index) {
            return index * 2 + 2;
        }

        private int compare(LongValue first, LongValue second) {
            if (comparator == null) {
                return first.compareTo(second);

            } else {
                return comparator.compare(first, second);
            }
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
