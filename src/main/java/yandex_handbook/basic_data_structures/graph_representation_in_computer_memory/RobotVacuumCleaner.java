package yandex_handbook.basic_data_structures.graph_representation_in_computer_memory;

import java.io.IOException;
import java.io.InputStream;

public class RobotVacuumCleaner {

    private static final FastScanner FS = new FastScanner(System.in);
    private static char[][] room;
    private static Robot robot;

    public static void main(String[] args) throws IOException {
        int n = FS.nextInt();
        int m = FS.nextInt();

        room = new char[n][m];
        fillRoom();

        int r = FS.nextInt();
        int c = FS.nextInt();

        room[r - 1][c - 1] = '!';

        robot = new Robot(c, r);

        int q = FS.nextInt();
        for (int i = 0; i < q; i++) {
            enterProgramm();
        }

        System.out.print("\n" + robot.visitedCells);
    }

    private static void enterProgramm() throws IOException {
        char action = FS.nextChar();
        switch (action) {
            case 'R' -> robot.turnRight();
            case 'L' -> robot.turnLeft();
            case 'M' -> robot.moveForward();
        }
    }

    private static void fillRoom() throws IOException {
        for (int i = 0; i < room.length; i++) {
            for (int j = 0; j < room[0].length; j++) {
                char cell = FS.nextChar();
                room[i][j] = cell;
            }
        }
    }

    static class Robot {

        int x;
        int y;
        Direction direction;
        int visitedCells = 1;

        Robot(int x, int y) {
            this.x = x;
            this.y = y;
            direction = Direction.NORTH;
        }

        public void turnRight() {
            direction = direction.right;
        }

        public void turnLeft() {
            direction = direction.left;
        }

        public void moveForward() {
            if (clearCellAhead()) {
                move();
                if (room[y - 1][x - 1] == '.') {
                    room[y - 1][x - 1] = '!';
                    visitedCells++;
                }

            }
        }

        private void move() {
            int[] nextCoordinates = calculateNextCoordinates(direction, x, y);
            x = nextCoordinates[0];
            y = nextCoordinates[1];
        }

        private boolean clearCellAhead() {
            int[] nextCoordinates = calculateNextCoordinates(direction, x, y);
            int x = nextCoordinates[0];
            int y = nextCoordinates[1];

            if (x - 1 < 0 || x > room[0].length) {
                return false;
            }

            if (y - 1 < 0 || y > room.length) {
                return false;
            }

            if (room[y - 1][x - 1] == '#') {
                return false;
            }

            return true;
        }

        private int[] calculateNextCoordinates(Direction direction, int x, int y) {
            return switch (direction) {
                case NORTH -> new int[]{x, y - 1};
                case EAST -> new int[]{x + 1, y};
                case SOUTH -> new int[]{x, y + 1};
                case WEST -> new int[]{x - 1, y};
            };
        }
    }

    enum Direction {

        NORTH,
        WEST,
        SOUTH,
        EAST;

        private Direction left;
        private Direction right;

        static {
            NORTH.left = WEST;
            NORTH.right = EAST;

            WEST.left = SOUTH;
            WEST.right = NORTH;

            SOUTH.left = EAST;
            SOUTH.right = WEST;

            EAST.left = NORTH;
            EAST.right = SOUTH;
        }

        Direction left() {
            return this.left;
        }

        Direction right() {
            return this.right;
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
