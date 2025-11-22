package yandex_handbook.basic_data_structures.price_drop;

import java.util.Scanner;

public class Solver {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static int minIndex = 0;
    private static int maxIndex = 0;
    private static int minValue = Integer.MAX_VALUE;
    private static int maxValue = Integer.MIN_VALUE;

    private static int minDiffValue = Integer.MAX_VALUE;
    private static int maxDiffValue = Integer.MIN_VALUE;
    private static int minDiffIIndex = 0;
    private static int minDiffJIndex = 1;
    private static int maxDiffIIndex = 0;
    private static int maxDiffJIndex = 1;

    public static void main(String[] args) {
        int numberOfElements = SCANNER.nextInt();
        int[] array = new int[numberOfElements];

        int firstElem = SCANNER.nextInt();

        array[0] = firstElem;

        minValue = firstElem;
        maxValue = firstElem;

        for (int i = 1; i < numberOfElements; i++) {
            int nextElem = SCANNER.nextInt();
            array[i] = nextElem;

            handleMin(nextElem, i);
            handleMax(nextElem, i);
        }

        System.out.println((minDiffIIndex + 1) + " " + (minDiffJIndex + 1));
        System.out.println((maxDiffIIndex + 1) + " " + (maxDiffJIndex + 1));
    }

    private static void handleMin(int nextElem, int i) {
        if (minValue - nextElem < minDiffValue) {
            minDiffValue = minValue - nextElem;
            minDiffIIndex = minIndex;
            minDiffJIndex = i;
        }

        if (nextElem < minValue) {
            minValue = nextElem;
            minIndex = i;
        }
    }

    private static void handleMax(int nextElem, int i) {
        if (maxValue - nextElem > maxDiffValue) {
            maxDiffValue = maxValue - nextElem;
            maxDiffIIndex = maxIndex;
            maxDiffJIndex = i;
        }

        if (nextElem > maxValue) {
            maxValue = nextElem;
            maxIndex = i;
        }
    }
}