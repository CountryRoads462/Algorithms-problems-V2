package yandex_handbook.basic_data_structures.map.search_for_the_three_most_frequent_elements_in_an_array;

import java.util.*;

public class Solver {

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        int[] arr = new int[100_001];
        TreeSet<int[]> counts = getSortedSet();

        int numberOfElements = SCANNER.nextInt();
        for (int i = 0; i < numberOfElements; i++) {
            int userInput = SCANNER.nextInt();
            int count = ++arr[userInput];
            counts.add(new int[]{userInput, count});
        }

        int three = 3;
        List<Integer> list = new ArrayList<>(5);
        for (int[] arr1 : counts) {
            list.add(arr1[0]);
            three--;
            if (three == 0) {
                break;
            }
        }

        list.sort(Integer::compareTo);

        System.out.println(String.join(" ", list.stream().map(String::valueOf).toList()));
    }

    private static CustomTreeSet getSortedSet() {
        return new CustomTreeSet((o1, o2) -> {
            if (o1[1] < o2[1]) {
                return 1;
            } else if (o1[1] == o2[1]) {
                return Integer.compare(o1[0], o2[0]);
            } else {
                return -1;
            }
        });
    }

    static class CustomTreeSet extends TreeSet<int[]> {

        public CustomTreeSet(Comparator<? super int[]> comparator) {
            super(comparator);
        }

        @Override
        public boolean add(int[] ints) {
            remove(new int[]{ints[0], ints[1] - 1});
            return super.add(ints);
        }
    }
}

//{0, 0}, {0, 0}, {0, 0} min 0
//{0, 0}, {0, 0}, {11, 1} min 0
//{0, 0}, {11, 1}, {21, 1} min 0
//{11, 1}, {21, 1}, {31, 1} min 1        {41,1}
//{11, 1}, {21, 1}, {31, 2}  min 1       {41,1}
//{11, 1},  {31, 2},  {41,2}  min 1      {21, 1}
