package org.example.UnionFind;

import java.util.HashMap;
import java.util.Map;

public class countPairs {

    public static long countPairs(int n, int[][] edges) {
        WeightedUnionFind unionFind = new WeightedUnionFind(n);
        for (int i = 0; i < edges.length; i++) {
            unionFind.union(edges[i][0], edges[i][1]);
        }

        // hashSet <root, number of element in group>
        Map<Integer, Integer> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int rootI = unionFind.find(i);
            int numsELe = groups.getOrDefault(rootI, 0) + 1;
            groups.put(rootI, numsELe);
        }

        long count = 0;

        // number of element in a group
        long remainELe = n;
        for (Integer numEle : groups.values()) {
            remainELe -= numEle;
            count += numEle * remainELe;
        }
        return count;
    }

    static class WeightedUnionFind {
        int parent[];
        int rank[];


        WeightedUnionFind(int n) {
            parent = new int[n];
            rank = new int[n];


            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        // find root of a node, may be parent of parent ...
        int find(int x) {
            // base case
            if (parent[x] == x)
                return x;
            else {
                parent[x] = find(parent[x]);
                return parent[x];
            }
        }

        // union x, y
        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY)
                return;

            if (rank[rootX] > rank[rootY])
                parent[rootY] = rootX;
            else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[x]++;
            }
        }


    }

    public static void main(String[] args) {
        // 0, 1, 2
        countPairs countPairs = new countPairs();
        System.out.println(countPairs(7, new int[][]{{0,2}, {0,5}, {2,4}, {1,6}, {5,4}}));

    }
}
