package yandex_handbook.basic_data_structures.map.similar_names;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solver {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int numberOfWords = Integer.parseInt(SCANNER.nextLine());

        Map<String, Integer> wildFreq = new HashMap<>();
        Map<String, Integer> fullFreq = new HashMap<>();

        int wordLength = 0;

        for (int i = 0; i < numberOfWords; i++) {
            String word = SCANNER.nextLine();
            wordLength = word.length();
            fullFreq.merge(word, 1, Integer::sum);

            for (int j = 0; j < word.length(); j++) {
                char[] charArr = word.toCharArray();
                charArr[j] = '*';
                String wildWord = new String(charArr);
                wildFreq.merge(wildWord, 1, Integer::sum);
            }
        }

        int total = getCount(wildFreq);
        int duplicate = getCount(fullFreq) * wordLength;

        System.out.println(total - duplicate);
    }

    private static int getCount(Map<String, Integer> map) {
        return map.values().stream()
                .mapToInt(Solver::handshakeFormule)
                .sum();
    }

    private static int handshakeFormule(int count) {
        return count * (count - 1) / 2;
    }
}
