package org.example.Sort;

import java.util.Arrays;

public class numSubseq {
    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        int result = 0;

        int[] exp = new int[nums.length];
        exp[0] = 1;

        for (int i = 1; i < nums.length; i++)
            exp[i] = (exp[i - 1] * 2) % 1000000007;

        // j - i indicate number of other number can combine with element in index i
        for (int i = 0, j = nums.length - 1; i <= j; i++) {
            if (nums[i] + nums[i] > target)
                break;
            while (i < j && nums[i] + nums[j] > target)
                j--;
            result = result % 1000000007 + exp[j - i];

        }

        return result;

    }

    public static void main(String[] args) {
        // 7
        System.out.println(new numSubseq().numSubseq(new int[]{3, 3, 5, 8}, 10));
        // 4
        System.out.println(new numSubseq().numSubseq(new int[]{3, 5, 6, 7}, 9));
        // 6
        System.out.println(new numSubseq().numSubseq(new int[]{3, 3, 6, 8}, 10));


        System.out.println(new numSubseq().numSubseq(new int[]{14, 4, 6, 6, 20, 8, 5, 6, 8, 12, 6, 10, 14, 9, 17, 16, 9, 7, 14, 11, 14, 15, 13, 11, 10, 18, 13, 17, 17, 14, 17, 7, 9, 5, 10, 13, 8, 5, 18, 20, 7, 5, 5, 15, 19, 14}, 22));
    }
}
