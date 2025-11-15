package yandex_handbook.basic_data_structures.performing_operations_with_a_list;

import java.util.Arrays;
import java.util.Scanner;

public class Solver {

    private static final Scanner SCANNER = new Scanner(System.in);

    private static Node HEAD = null;

    public static void main(String[] args) {
        int numberOfQueries = Integer.valueOf(SCANNER.nextLine());

        for (int i = 0; i < numberOfQueries; i++) {
            int[] userInput = readUserInput();

            int queryType = userInput[0];

            switch (queryType) {
                case 1 -> executeFirstQuery(userInput[1], userInput[2]);
                case 2 -> executeSecondQuery(userInput[1]);
                case 3 -> executeThirdQuery(userInput[1]);
            }
        }
    }

    private static void executeThirdQuery(int nodeNumber) {
        if (nodeNumber == 1) {
            HEAD = HEAD.next;

        } else {
            Node node = HEAD;
            for (int i = 1; i < nodeNumber - 1; i++) {
                node = node.next;
            }

            Node nodeNextAfterDeleted = node.next.next;

            node.next = nodeNextAfterDeleted;
        }
    }

    private static void executeSecondQuery(int nodeNumber) {
        Node node = HEAD;
        for (int i = 1; i < nodeNumber; i++) {
            node = node.next;
        }

        System.out.println(node.value);
    }

    private static void executeFirstQuery(int nodeNumber, int value) {
        if (nodeNumber == 0) {
            HEAD = new Node(value, HEAD);

        } else {
            Node node = HEAD;
            for (int i = 1; i < nodeNumber; i++) {
                node = node.next;
            }

            node.next = new Node(value, node.next);
        }
    }

    private static int[] readUserInput() {
        String userInput = SCANNER.nextLine();

        return Arrays.stream(userInput.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    private static class Node {

        private int value;
        private Node next;

        private Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }
}
