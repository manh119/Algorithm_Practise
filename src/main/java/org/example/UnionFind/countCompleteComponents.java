package org.example.UnionFind;

import java.util.HashMap;
import java.util.Map;

public class countCompleteComponents {
    public int countCompleteComponents(int n, int[][] edges) {
        WeightedUnionFind unionFind = new WeightedUnionFind(n);
        for (int[] edge : edges) {
            unionFind.union(edge[0], edge[1]);
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (unionFind.find(i) == i && unionFind.sizeInGroup(i) * (unionFind.sizeInGroup(i) - 1) / 2 == unionFind.edgeInGroup(i))
                count++;
        }
        return count;
    }

    static class WeightedUnionFind {
        int[] parent;
        int[] rank;
        int[] sizeInGroup;
        int[] edgeInGroup;

        WeightedUnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            sizeInGroup = new int[n];
            edgeInGroup = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                sizeInGroup[i] = 1;
                edgeInGroup[i] = 0;
            }

        }

        int sizeInGroup(int x) {
            return sizeInGroup[x];
        }

        int edgeInGroup(int x) {
            return edgeInGroup[x];
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

            if (rootX == rootY) {
                edgeInGroup[rootX]++;
                return;
            }

            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
                sizeInGroup[rootX] += sizeInGroup[rootY];
                edgeInGroup[rootX] += edgeInGroup[rootY] + 1;
            }
            else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
                sizeInGroup[rootY] += sizeInGroup[rootX];
                edgeInGroup[rootY] += edgeInGroup[rootX] + 1;
            } else {
                sizeInGroup[rootX] += sizeInGroup[rootY];
                edgeInGroup[rootX] += edgeInGroup[rootY] + 1;
                parent[rootY] = rootX;
                rank[x]++;
            }
        }
    }

    public static void main(String[] args) {
        countCompleteComponents completeComponents = new countCompleteComponents();
        System.out.println(        completeComponents.countCompleteComponents(3, new int[][] {{1,0}, {2,0}}));
    }
}
