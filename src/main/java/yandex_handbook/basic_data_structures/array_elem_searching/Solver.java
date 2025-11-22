package yandex_handbook.basic_data_structures.array_elem_searching;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class Solver {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int numberOfElements = SCANNER.nextInt();
        int numberOfQueries = SCANNER.nextInt();

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < numberOfElements; i++) {
            map.putIfAbsent(SCANNER.nextInt(), i);
        }

        for (int i = 0; i < numberOfQueries; i++) {
            int numberToFind = SCANNER.nextInt();
            int foundIndex = Optional.ofNullable(map.get(numberToFind)).map(in -> in + 1).orElse(-1);
            System.out.println(foundIndex);
        }
    }
}
