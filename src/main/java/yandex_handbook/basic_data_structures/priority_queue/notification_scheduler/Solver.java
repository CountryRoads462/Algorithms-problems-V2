    package yandex_handbook.basic_data_structures.priority_queue.notification_scheduler;

    import java.io.IOException;
    import java.io.InputStream;

    public class Solver {

        private static final FastScanner FS = new FastScanner(System.in);

        public static void main(String[] args) throws IOException {
            int n = FS.nextInt();
            int m = FS.nextInt();

            Notificator notifications = new Notificator(n);

            for (int i = 0; i < n; i++) {
                int id = FS.nextInt();
                int p = FS.nextInt();
                int s = FS.nextInt();

                notifications.add(new Notification(id, p, s));
            }

            for (int i = 0; i < m; i++) {
                System.out.println(notifications.execNotify());
            }
        }

        private static class Notificator {

            Notification[] arr;
            int size;

            Notificator(int n) {
                arr = new Notification[n];
            }

            public void add(Notification notification) {
                arr[size] = notification;
                shiftUp(size);
                size++;
            }

            private void shiftUp(int index) {
                int parent = parent(index);

                if (index != 0 && arr[index].compareTo(arr[parent]) < 0) {
                    swap(index, parent);
                    shiftUp(parent);
                }
            }

            private int parent(int index) {
                return (index - 1) / 2;
            }

            private void swap(int i1, int i2) {
                Notification temp = arr[i1];
                arr[i1] = arr[i2];
                arr[i2] = temp;
            }

            public int execNotify() {
                Notification root = arr[0];
                root.nextNotification = root.nextNotification + root.p;
                shiftDown(0);
                return root.id;
            }

            private void shiftDown(int index) {
                int smallest = index;

                int left = left(index);
                if (left < size && arr[left].compareTo(arr[smallest]) < 0) {
                    smallest = left;
                }

                int right = right(index);
                if (right < size && arr[right].compareTo(arr[smallest]) < 0) {
                    smallest = right;
                }

                if (index != smallest) {
                    swap(smallest, index);
                    shiftDown(smallest);
                }
            }

            private int left(int index) {
                return 2 * index + 1;
            }

            private int right(int index) {
                return 2 * index + 2;
            }
        }

        private static class Notification implements Comparable<Notification> {

            final int id;
            final int p;
            final int s;
            long nextNotification;

            Notification(int id, int p, int s) {
                this.id = id;
                this.p = p;
                this.s = s;
                this.nextNotification = s;
            }

            @Override
            public int compareTo(Notification o) {
                int compared = Long.compare(nextNotification, o.nextNotification);
                if (compared == 0) {
                    return Integer.compare(id, o.id);
                } else {
                    return compared;
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
