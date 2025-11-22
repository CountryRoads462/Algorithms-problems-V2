package yandex_handbook.basic_data_structures.minimum_on_the_left;

import java.util.Scanner;

public class Solver {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int numberOfElements = SCANNER.nextInt();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < numberOfElements; i++) {
            int userInput = SCANNER.nextInt();
            if (userInput < min) {
                min = userInput;

            }
            System.out.print(min + " ");
        }
    }
}
