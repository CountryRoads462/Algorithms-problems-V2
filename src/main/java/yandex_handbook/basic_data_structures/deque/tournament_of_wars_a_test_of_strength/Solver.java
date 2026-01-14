package yandex_handbook.basic_data_structures.deque.tournament_of_wars_a_test_of_strength;

import java.io.IOException;
import java.io.InputStream;

public class Solver {

    private static final FastScanner SCANNER = new FastScanner(System.in);

    private static Warrior head;

    public static void main(String[] args) throws IOException {
        int numberOfWarriors = SCANNER.nextInt();

        Warrior firstWarrior = new Warrior(SCANNER.nextInt());
        head = firstWarrior;
        Warrior prevWarrior = firstWarrior;

        for (int i = 1; i < numberOfWarriors; i++) {
            int strength = SCANNER.nextInt();
            Warrior newWarrior = new Warrior(strength);
            prevWarrior.rightNeighbour = newWarrior;
            newWarrior.leftNeighbour = prevWarrior;

            if (i == numberOfWarriors - 1) {
                firstWarrior.leftNeighbour = newWarrior;
                newWarrior.rightNeighbour = firstWarrior;

            } else {
                prevWarrior = newWarrior;
            }
        }

        if (numberOfWarriors == 1) {
            System.out.print(firstWarrior.strength + " " + firstWarrior.strength);
        } else {
            startRound(firstWarrior, numberOfWarriors, firstWarrior);
        }
    }

    private static void startRound(Warrior current, int numberOfWarriors, Warrior prevWinner) {
        if (numberOfWarriors == 2) {
            System.out.print(prevWinner.strength + " " + prevWinner.rightNeighbour.strength);

        } else {
            Warrior leftWarrior = current.leftNeighbour;
            Warrior rightWarrior = current.rightNeighbour;

            Warrior weakestWarrior = current;
            Warrior strongestWarrior = current;

            if (leftWarrior.strength < weakestWarrior.strength) {
                weakestWarrior = leftWarrior;
            }

            if (rightWarrior.strength < weakestWarrior.strength) {
                weakestWarrior = rightWarrior;
            }

            if (leftWarrior.strength > strongestWarrior.strength) {
                strongestWarrior = leftWarrior;
            }

            if (rightWarrior.strength > strongestWarrior.strength) {
                strongestWarrior = rightWarrior;
            }

            eliminateFromTournament(weakestWarrior);

            if (weakestWarrior == current) {
                startRound(strongestWarrior, numberOfWarriors - 1, head);

            } else {
                startRound(strongestWarrior, numberOfWarriors - 1, current);
            }
        }
    }

    private static void eliminateFromTournament(Warrior weakestWarrior) {
        Warrior left = weakestWarrior.leftNeighbour;
        Warrior right = weakestWarrior.rightNeighbour;

        left.rightNeighbour = right;
        right.leftNeighbour = left;

        if (head == weakestWarrior) {
            head = right;
        }
    }

    static class Warrior {

        Warrior rightNeighbour;
        Warrior leftNeighbour;

        int strength;

        Warrior(int strength) {
            this.strength = strength;
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
