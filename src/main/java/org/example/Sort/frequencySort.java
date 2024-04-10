package org.example.Sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class frequencySort {
    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
        }

        int[][] pair = new int[nums.length][2];

        for (int i = 0; i < nums.length; i++) {
            pair[i][0] = nums[i];
            pair[i][1] = freqMap.get(nums[i]);
        }

        Arrays.sort(pair, (a, b) -> a[1] == b[1] ? b[0] - a[0] : a[1] - b[1]);

        for (int i = 0; i < nums.length; i++) {
            nums[i] = pair[i][0];
        }
        return nums;
    }

    public static void main(String[] args) {
        new frequencySort().frequencySort(new int[]{1, 1, 2, 2, 2, 3});
    }
}
