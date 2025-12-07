package yandex_handbook.basic_data_structures.last_max_deleting;

import java.util.Scanner;

public class Solver {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int lastMaxIndex = 0;
        int lastMaxValue = Integer.MIN_VALUE;

        int numberOfElements = SCANNER.nextInt();
        int[] arr = new int[numberOfElements];

        for (int i = 0; i < numberOfElements; i++) {
            arr[i] = SCANNER.nextInt();
            if (arr[i] >= lastMaxValue) {
                lastMaxValue = arr[i];
                lastMaxIndex = i;
            }
        }

        for (int i = 0; i < numberOfElements; i++) {
            if (i != lastMaxIndex) {
                System.out.print(arr[i] + " ");
            }
        }
    }
}
