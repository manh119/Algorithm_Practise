package org.example.UnionFind;


// implement DisjointUnionSets by use path compression and union by rank.
// A Java program to implement Disjoint Set Data
// Structure.

import java.io.*;
import java.util.*;

class DisjointUnionSets {
    int[] rank, parent;
    int n;

    // Constructor
    public DisjointUnionSets(int n)
    {
        rank = new int[n];
        parent = new int[n];
        this.n = n;
        makeSet();
    }

    // Creates n sets with single item in each
    void makeSet()
    {
        for (int i = 0; i < n; i++) {
            // Initially, all elements are in
            // their own set.
            parent[i] = i;
        }
    }

    // Returns representative of x's set
    int find(int x)
    {
        // Finds the representative of the set
        // that x is an element of
        if (parent[x] != x) {
            // if x is not the parent of itself
            // Then x is not the representative of
            // his set,
            //parent[x] = find(parent[x]);

            // so we recursively call Find on its parent
            // and move i's node directly under the
            // representative of this set
        }

        return parent[x];
    }

    // Unites the set that includes x and the set
    // that includes x
    void union(int x, int y)
    {
        // Find representatives of two sets
        int xRoot = find(x), yRoot = find(y);

        // Elements are in the same set, no need
        // to unite anything.
        if (xRoot == yRoot)
            return;

        // If x's rank is less than y's rank
        if (rank[xRoot] < rank[yRoot])

            // Then move x under y  so that depth
            // of tree remains less
            parent[xRoot] = yRoot;

            // Else if y's rank is less than x's rank
        else if (rank[yRoot] < rank[xRoot])

            // Then move y under x so that depth of
            // tree remains less
            parent[yRoot] = xRoot;

        else // if ranks are the same
        {
            // Then move y under x (doesn't matter
            // which one goes where)
            parent[yRoot] = xRoot;

            // And increment the result tree's
            // rank by 1
            rank[xRoot] = rank[xRoot] + 1;
        }
    }


// Driver code

    public static void main(String[] args) {
        // Let there be 5 persons with ids as
        // 0, 1, 2, 3 and 4
        int n = 8;
        DisjointUnionSets dus =
                new DisjointUnionSets(n);

        dus.union(0, 1);
        dus.union(5, 6);
        dus.union(2, 3);
        dus.union(4, 5);
        dus.union(1, 2);
        dus.union(6, 7);
        dus.union(3, 4);
        dus.find(4);dus.find(6);
        dus.find(7);




    }
}

