package org.example.Sort;

import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class filterRestaurants {
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        return Arrays
                .stream(restaurants)
                .filter(ints -> ints[2] >= veganFriendly && ints[3] <= maxPrice && ints[4] <= maxDistance)
                .sorted((res1, res2) -> res1[1] == res2[1] ? res2[0] - res1[0] : res2[1] - res1[1])
                .map(ints -> ints[0])
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[][] restaurants = {{1, 4, 1, 40, 10}, {2, 8, 0, 50, 5}, {3, 8, 1, 30, 4}, {4, 10, 0, 10, 3}, {5, 1, 1, 15, 1}};
        int veganFriendly = 0, maxPrice = 50, maxDistance = 10;

        List<Integer> filtered = Arrays
                .stream(restaurants)
                .filter(ints -> ints[2] >= veganFriendly && ints[3] <= maxPrice && ints[4] <= maxDistance)
                .sorted((res1, res2) -> res1[1] == res2[1] ? res2[0] - res1[0] : res2[1] - res1[1])
                .map(ints -> ints[0])
                .collect(Collectors.toList());
        System.out.println(filtered);


    }
}
