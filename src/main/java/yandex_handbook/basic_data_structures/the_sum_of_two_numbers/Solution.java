package yandex_handbook.basic_data_structures.the_sum_of_two_numbers;

import java.util.Arrays;
import java.util.Scanner;

class Solution {

    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        int n = SC.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = SC.nextInt();
        }

        int[] result = productExceptSelf(nums);

        System.out.println(Arrays.toString(result));
    }

    public static int[] productExceptSelf(int[] nums) {
        int len = nums.length;

        int[] left = new int[len];
        int[] right = new int[len];

        for (int i = 0, j = len - 1; i < len - 1; i++, j--) {
            if (i - 1 >= 0) {
                left[i] = left[i - 1] * nums[i];
            } else {
                left[i] = nums[i];
            }

            if (j + 1 < len) {
                right[j] = right[j + 1] * nums[j];
            } else {
                right[j] = nums[j];
            }
        }

        for (int i = 0; i < len; i++) {
            if (i == 0) {
                nums[i] = right[i + 1];
            } else if (i == len - 1) {
                nums[i] = left[i - 1];
            } else {
                nums[i] = left[i - 1] * right[i + 1];
            }
        }

        return nums;
    }
}
