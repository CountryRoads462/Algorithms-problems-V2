package yandex_handbook.basic_data_structures.map.search_for_the_most_frequent_fraction_in_an_array;

import java.io.IOException;
import java.io.InputStream;

public class Solver {

    private static final FastScanner FS = new FastScanner(System.in);

    public static void main(String[] args) throws Exception {
        int numberOfElements = FS.nextInt();

        int frequentNumenator = 0;
        int frequentDenumenator = 0;

        int[][] matrix = new int[1001][1001];

        for (int i = 0; i < numberOfElements; i++) {
            int numerator = FS.nextInt();
            int denominator = FS.nextInt();

            int[] abbreviatedFraction = abbreviateFraction(numerator, denominator);
            int abbreviatedNumenator = abbreviatedFraction[0];
            int abbreviatedDenominator = abbreviatedFraction[1];

            int incrementedCount = ++matrix[abbreviatedNumenator][abbreviatedDenominator];
            if (incrementedCount > matrix[frequentNumenator][frequentDenumenator]) {
                frequentNumenator = abbreviatedNumenator;
                frequentDenumenator = abbreviatedDenominator;

            } else if (incrementedCount == matrix[frequentNumenator][frequentDenumenator]
                    && compareFractions(abbreviatedNumenator, abbreviatedDenominator, frequentNumenator, frequentDenumenator)) {

                frequentNumenator = abbreviatedNumenator;
                frequentDenumenator = abbreviatedDenominator;
            }
        }

        System.out.println(frequentNumenator + " " + frequentDenumenator);
    }

    public static boolean compareFractions(int a, int b, int c, int d) {
        long left = (long) a * d;
        long right = (long) c * b;

        return left < right;
    }

    private static int[] abbreviateFraction(int numerator, int denominator) {
        int gcd = findGreatestCommonDivisor(numerator, denominator);

        return new int[]{numerator / gcd, denominator / gcd};
    }

    private static int findGreatestCommonDivisor(int numerator, int denominator) {
        int remainder = numerator % denominator;
        if (remainder == 0) {
            return denominator;

        } else {
            return findGreatestCommonDivisor(denominator, remainder);
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
