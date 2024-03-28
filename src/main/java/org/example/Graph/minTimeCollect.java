package org.example.Graph;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class minTimeCollect {
//    public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
//        /**
//         1. Build an adj list from given edge array
//         2. Apply DFS
//         */
//        Map<Integer, List<Integer>> graph = createGraph(edges);
//        return dfs(0,-9, graph, hasApple);
//
//    }
//
//    // return number of steps
//    private int dfs(int node, int parent, Map<Integer, List<Integer>> graph, List<Boolean> hasApple) {
//        int steps = 0;
//        for(Integer adj : graph.get(node)) {
//            if(adj != parent)
//                steps += dfs(adj, node, graph, hasApple);
//        }
//        if(node != 0 && (hasApple.get(node) || steps != 0))
//            steps += 2;
//        return steps;
//    }
//
//    private static Map<Integer, List<Integer>> createGraph(int[][] edges) {
//        Map<Integer, List<Integer>> graph = new HashMap<>();
//        for(int[] edge : edges) {
//            List<Integer> list = graph.getOrDefault(edge[0], new ArrayList<>());
//            list.add(edge[1]);
//            graph.put(edge[0], list);
//
//            list = graph.getOrDefault(edge[1], new ArrayList<>());
//            list.add(edge[0]);
//            graph.put(edge[1], list);
//        }
//        return graph;
//    }
public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
    /**
     1. Build an adj list from given edge array
     2. Apply DFS
     */

    Map<Integer, List<Integer>> adj = new HashMap<>();
    for (int[] edge: edges) {
        int a = edge[0], b = edge[1];
        adj.computeIfAbsent(a, value -> new ArrayList<Integer>()).add(b);
        adj.computeIfAbsent(b, value -> new ArrayList<Integer>()).add(a);
    }

    return dfs(0, -1, adj, hasApple);
}

    private int dfs(int node, int parent, Map<Integer, List<Integer>> adj, List<Boolean> hasApple) {
        int steps = 0;
        for (int nbr: adj.get(node)) {
            if (nbr != parent) {
                steps += dfs(nbr, node, adj, hasApple);
            }
        }

        if ((node != 0) && (hasApple.get(node) || steps != 0)) {
            steps += 2;
        }

        return steps;
    }


    public static void main(String[] args) {
        int[][] edges = {};
        List<Boolean> hasApple = new ArrayList<>();
        List<Boolean> c = new ArrayList<>();
        c.add(true);
        hasApple.addAll(c);
        System.out.println(new minTimeCollect().minTime(7, edges, hasApple));
    }
}
