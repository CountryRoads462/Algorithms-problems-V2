package yandex_handbook.basic_data_structures.priority_queue.support_center;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.PriorityQueue;

public class Solver {

    private static final FastScanner FS = new FastScanner(System.in);

    public static void main(String[] args) throws IOException {
        int numberOfTickets = FS.nextInt();


        PriorityQueue<Ticket> queue = new PriorityQueue<>((o1, o2) -> {
            int compared = Integer.compare(o2.priority, o1.priority);
            if (compared == 0) {
                return Integer.compare(o1.duration, o2.duration);
            } else {
                return compared;
            }
        });

        for (int i = 0; i < numberOfTickets; i++) {
            int duration = FS.nextInt();
            int priority = FS.nextInt();

            Ticket ticket = new Ticket(duration, priority);
            queue.add(ticket);
        }

        BigInteger first = BigInteger.ZERO;
        BigInteger second = BigInteger.ZERO;

        while (!queue.isEmpty()) {
            Ticket mostImportantTicket = queue.poll();

            if (first.compareTo(second) < 0) {
                first = first.add(BigInteger.valueOf(mostImportantTicket.duration));
            } else {
                second = second.add(BigInteger.valueOf(mostImportantTicket.duration));
            }
        }

        if (first.compareTo(second) > 0) {
            System.out.println(first);
        } else {
            System.out.println(second);
        }
    }

    private static class Ticket {

        int duration;
        int priority;

        Ticket(int duration, int priority) {
            this.duration = duration;
            this.priority = priority;
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
