package yandex_handbook.basic_data_structures.local_minimum_deleting;

import java.util.Scanner;

public class Solver {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int numberOfElements = SCANNER.nextInt();
        int left = numberOfElements;
        int[][] arr = new int[numberOfElements][2];

        for (int i = 0; i < numberOfElements; i++) {
            arr[i][0] = SCANNER.nextInt();
            arr[i][1] = 0;
        }

        for (int i = 1; i < numberOfElements - 1; i++) {
            int currentElem = arr[i][0];
            if (arr[i - 1][0] > currentElem && currentElem < arr[i + 1][0]) {
                arr[i][1] = 1;
                left--;
            }
        }

        System.out.println(left);
        for (int i = 0; i < numberOfElements; i++) {
            if (arr[i][1] == 0) {
                System.out.print(arr[i][0] + " ");
            }
        }
    }
}
