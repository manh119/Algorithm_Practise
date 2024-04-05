package org.example.Set;

import Algorithms_Part_1.Percolation.InteractivePercolationVisualizer;
import edu.princeton.cs.algs4.In;

import java.util.HashSet;
import java.util.Set;

public class longestConsecutive {
    public int longestConsecutive(int[] nums) {
        if (nums.length == 0)
            return 0;

        HashSet<Integer> set = new HashSet<>();
        for (int i : nums)
            set.add(i);

        int max = 1;

        for (int x : set) {
            int longest = 1;
            if (!set.contains(x - 1)) {
                while (set.contains(x + 1)) {
                    longest++;
                    x++;
                }
                if (longest > max)
                    max = longest;
            }
        }
        return max;
    }
}
