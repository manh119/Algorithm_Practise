package org.example.Sort;

import java.util.*;

public class minDeletions {
        public int minDeletions(String s) {
            int[] freq = new int[26];
            // aaabbbcc => a - 3, b - 3, c - 2
            for(char c : s.toCharArray()) {
                freq[c - 'a'] += 1;
            }

            PriorityQueue<Integer> list = new PriorityQueue<>(Collections.reverseOrder());
            for (int i = 0; i < freq.length; i++) {
                if (freq[i] > 0) list.add(freq[i]);
            };

            int count = 0;

            while(!list.isEmpty()) {
                Integer x = list.poll();
                while (list.contains(x) && x!= 0) {
                    count++;
                    list.poll();
                    list.add( x - 1);
                }
            }


            return count;
        }

    public static void main(String[] args) {
        System.out.println(new minDeletions().minDeletions("bbcebab"));
    }

}
