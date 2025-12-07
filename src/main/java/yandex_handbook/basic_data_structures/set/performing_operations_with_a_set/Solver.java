package yandex_handbook.basic_data_structures.set.performing_operations_with_a_set;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solver {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Set<Integer> SET = new HashSet<>();

    public static void main(String[] args) {
        int numberOfQueries = SCANNER.nextInt();

        for (int i = 0; i < numberOfQueries; i++) {
            int query = SCANNER.nextInt();
            int value = SCANNER.nextInt();

            executeQuery(query, value);
        }
    }

    private static void executeQuery(int query, int value) {
        switch (query) {
            case 1 -> executeFirstQuery(value);
            case 2 -> executeSecondQuery(value);
        }
    }

    private static void executeFirstQuery(int value) {
        SET.add(value);
    }

    private static void executeSecondQuery(int value) {
        boolean contains = SET.contains(value);
        System.out.println(contains ? 1 : 0);
    }
}
