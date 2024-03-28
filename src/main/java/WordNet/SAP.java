package WordNet;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class SAP {
    private final Digraph G;
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph G) {
        if(G == null)
            throw new IllegalArgumentException("parameter G should not be null value");
        this.G = new Digraph(G);
    }

    // TODO : don't repeat yourself
    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w) {
        BreadthFirstDirectedPaths dfsV = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths dfsW = new BreadthFirstDirectedPaths(G, w);
        int min = Integer.MAX_VALUE;
        int result = -1;
        for (int x = 0; x < G.V(); x++) {
            if(dfsV.hasPathTo(x) && dfsW.hasPathTo(x)) {
                int disV2W = dfsV.distTo(x) + dfsW.distTo(x);
                if(disV2W < min) {
                    min = disV2W;
                    result = x;
                }
            }

        }
        if(result == -1) return -1; else return min;
    }

    // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        // directed path from v -> x
        // directed path from w -> x
        // for each edge x, find all directed path v->x and w->x use dfs, and return min total length
        BreadthFirstDirectedPaths dfsV = new BreadthFirstDirectedPaths(G, v);
        BreadthFirstDirectedPaths dfsW = new BreadthFirstDirectedPaths(G, w);
        int min = Integer.MAX_VALUE;
        int ancestor = -1;
        for (int x = 0; x < G.V(); x++) {
            if(dfsV.hasPathTo(x) && dfsW.hasPathTo(x)) {
                int disV2W = dfsV.distTo(x) + dfsW.distTo(x);
                if(disV2W < min) {
                    min = disV2W;
                    ancestor = x;
                }
            }

        }
        return ancestor;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null)
            throw new IllegalArgumentException("parameter should not be null value");

        for(Integer i : v) {
            if(i == null)
                throw new IllegalArgumentException("parameter should not be null value");
        }

        for(Integer j : w) {
            if(j == null)
                throw new IllegalArgumentException("parameter should not be null value");
        }
        int min = Integer.MAX_VALUE;
        int x = -1;
        for (int i : v)
            for(int j : w) {
                int lengthIJ = length(i, j);
                if(lengthIJ < min) {
                    x = ancestor(i,j);
                    min = lengthIJ;
                }
            }
        if(x == -1) return -1; else return min;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if (v == null || w == null)
            throw new IllegalArgumentException("parameter should not be null value");

        for(Integer i : v) {
            if(i == null)
                throw new IllegalArgumentException("parameter should not be null value");
        }

        for(Integer j : w) {
            if(j == null)
                throw new IllegalArgumentException("parameter should not be null value");
        }

        int min = Integer.MAX_VALUE;
        int x = -1;
        for (int i : v)
            for(int j : w) {
                int lengthIJ = length(i, j);
                if(lengthIJ < min) {
                    x = ancestor(i,j);
                    min = lengthIJ;
                }
            }
        return x;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        In in = new In("wordnet/digraph1.txt");
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);
        //while (!StdIn.isEmpty()) {
            //int v = StdIn.readInt();
            //int w = StdIn.readInt();
            int v = 0;
            int w = 3;
            int length   = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        System.out.println("-----------AFTER------------");
        G.addEdge(3,0);
        length   = sap.length(v, w);
        ancestor = sap.ancestor(v, w);
        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        //}


    }
}

