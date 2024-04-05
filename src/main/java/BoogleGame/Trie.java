package BoogleGame;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.TrieSET;

// data structure is customized for search prefix query
public class Trie {
    private static final int R = 256;
    private Node root;
    private int n;

    Trie() {
    }

    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        } else {
            Node x = this.get(this.root, key, 0);
            return x == null ? false : x.isString;
        }
    }

    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        } else if (d == key.length()) {
            return x;
        } else {
            char c = key.charAt(d);
            return this.get(x.next[c], key, d + 1);
        }
    }

    // TODO : write function to check a string is a prefix of any word in Trie
    public boolean havePrefixOf(String key) {
        Node x = this.get(this.root, key, 0);
        return x != null;
    }

    private Node add(Node x, String key, int d) {
        if (x == null) {
            x = new Node();
        }

        if (d == key.length()) {
            if (!x.isString) {
                ++this.n;
            }

            x.isString = true;
        } else {
            char c = key.charAt(d);
            x.next[c] = this.add(x.next[c], key, d + 1);
        }

        return x;
    }

    public void add(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to add() is null");
        } else {
            this.root = this.add(this.root, key, 0);
        }
    }


    public static void main(String[] args) {
        String[] dictionary;
        String fileDictionary = "boggle/dictionary-small.txt";
        In in = new In(fileDictionary);
        dictionary = in.readAllStrings();

        Trie trie = new Trie();

        for (String s : dictionary)
            trie.add(s);

        System.out.println(trie.havePrefixOf("U"));

    }

    private static class Node {
        private Node[] next;
        private boolean isString;

        private Node() {
            this.next = new Node[256];
        }
    }
}
