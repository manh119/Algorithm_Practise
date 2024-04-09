package org.example.Sort;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class filterRestaurants {
    class Pair implements Comparable<Pair> {
        int id;
        int rate;
        Pair(int id, int rate) {
            this.id = id;
            this.rate = rate;
        }

        @Override
        public int compareTo(Pair o) {
            if (this.rate == o.rate)
                return o.id - this.id;
            else
                return o.rate - this.rate;
        }
    }
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for(int[] res : restaurants) {
            if (res[2] >= veganFriendly && res[3] <= maxPrice && res[4] <= maxDistance)
                pq.add(new Pair(res[0], res[1]));
        }
        List<Integer> ans= new ArrayList<>();
        while(!pq.isEmpty()) {
            ans.add(pq.poll().id);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] restaurants = {{1, 4, 1, 40, 10}, {2, 8, 0, 50, 5}, {3, 8, 1, 30, 4}, {4, 10, 0, 10, 3}, {5, 1, 1, 15, 1}};
        int veganFriendly = 0, maxPrice = 50, maxDistance = 10;
        filterRestaurants filterRestaurants = new filterRestaurants();
        System.out.println(filterRestaurants.filterRestaurants(restaurants, veganFriendly, maxPrice, maxDistance));

    }
}
