package yandex_handbook.basic_data_structures.set.sunflower;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class Solver {

    private static final FastScanner FS = new FastScanner(System.in);

    public static void main(String[] args) throws Exception {
        int numberOfSets = FS.nextInt();
        int[] petalsNumber = new int[numberOfSets];

        Set<Integer> petals = new HashSet<>();

        int firstSetSize = FS.nextInt();
        Set<Integer> firstSet = new HashSet<>(firstSetSize);
        for (int i = 0; i < firstSetSize; i++) {
            int userInput = FS.nextInt();
            firstSet.add(userInput);
            petals.add(userInput);
        }

        Set<Integer> core = new HashSet<>();

        int secondSetSize = FS.nextInt();
        for (int i = 0; i < secondSetSize; i++) {
            int number = FS.nextInt();
            if (firstSet.contains(number)) {
                petals.remove(number);
                core.add(number);
            } else {
                petalsNumber[1]++;
                petals.add(number);
            }
        }

        petalsNumber[0] = firstSetSize - core.size();

        boolean result = proccess(numberOfSets, core, petals, petalsNumber);

        if (result) {
            System.out.println("YES");
            System.out.println(core.size());
            for (int i = 0; i < numberOfSets; i++) {
                System.out.print(petalsNumber[i] + " ");
            }
        } else {
            System.out.println("NO");
        }
    }

    private static boolean proccess(
            int numberOfSets,
            Set<Integer> core,
            Set<Integer> petals,
            int[] petalsNumber
    ) throws IOException {
        for (int i = 2; i < numberOfSets; i++) {
            int numberOfElems = FS.nextInt();
            int counter = 0;
            for (int j = 0; j < numberOfElems; j++) {
                int userInput = FS.nextInt();
                if (core.contains(userInput)) {
                    counter++;
                    if (counter > core.size()) {
                        return false;
                    }
                } else if (petals.contains(userInput)) {
                    return false;

                } else {
                    petalsNumber[i]++;
                    petals.add(userInput);
                }
            }

            if (counter != core.size()) {
                return false;
            }
        }

        return true;
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
