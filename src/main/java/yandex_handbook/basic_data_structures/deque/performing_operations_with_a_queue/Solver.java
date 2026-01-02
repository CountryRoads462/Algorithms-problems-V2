package yandex_handbook.basic_data_structures.deque.performing_operations_with_a_queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Solver {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Deque<Integer> DEQUE = new LinkedList<>();

    public static void main(String[] args) {
        int numberOfQueries = SCANNER.nextInt();

        for (int i = 0; i < numberOfQueries; i++) {
            int queryType = SCANNER.nextInt();

            try {
                switch (queryType) {
                    case 1 -> handleFirstQuery();
                    case 2 -> handleSecondQuery();
                }
            } catch (Exception _) {
            }

            System.out.println(!DEQUE.isEmpty() ? DEQUE.getFirst() : -1);
        }
    }

    private static void handleSecondQuery() {
        DEQUE.removeFirst();
    }

    private static void handleFirstQuery() {
        int number = SCANNER.nextInt();
        DEQUE.addLast(number);
    }
}
