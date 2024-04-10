package org.example.Sort;

import java.util.Arrays;

public class minDifference {
    public int minDifference(int[] nums) {
        if (nums.length <= 4)
            return 0;
        // > 5 element - minDifferentce is obtained by remove 3 of combining of 3 largest and 3 smallest
        Arrays.sort(nums);
        int a = nums[nums.length - 4] - nums[0];
        int b = nums[nums.length -3] - nums[1];
        int c = nums[nums.length - 2] - nums[2];
        int d = nums[nums.length -1] - nums[3];
        return Math.min(a, Math.min(b, Math.min(c,d)));

    }
}
