package org.example.Sort;

import edu.princeton.cs.algs4.In;

import java.util.*;

public class minSetSize {
    public int minSetSize(int[] arr) {
        // 3,3,3,3,5,5,5,2,2,7 => 4 3 2 1 in priorityQueue
        // 3 3 3 3             => 4 in priorityQueue
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();

        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int key : frequencyMap.keySet())
            pq.add(frequencyMap.get(key));

        frequencyMap.values();


        int total = 0;
        int ans = 0;
        while (total < arr.length / 2) {
            total += pq.remove();
            ans++;
        }

        return ans;
    }

    public static void main(String[] args) {
        minSetSize minSetSize = new minSetSize();
        minSetSize.minSetSize(new int[] {3,3,3,3,5,5,5,2,2,7});
        minSetSize.minSetSize(new int[] {3,3,3,3});

    }
}
