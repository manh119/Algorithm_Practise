package org.example.UnionFind;

import edu.princeton.cs.algs4.QuickFindUF;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Redundant_Connection {
    public int[] findRedundantConnection(int[][] edges) {
        UnionFind unionFind = new UnionFind(edges.length + 1);
        for (int i = 0; i < edges.length; i++) {
            int vertex1 = edges[i][0];
            int vertex2 = edges[i][1];
            if (!unionFind.union(vertex1,vertex2))
                return edges[i];
        }
        return new int[0];
    }

    class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return false;
            }
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX] += 1;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Redundant_Connection redundantConnection = new Redundant_Connection();
        int[][] edges = {{1,2}, {1,3}, {2,3}};
        System.out.println(redundantConnection.findRedundantConnection(edges));

    }
}
