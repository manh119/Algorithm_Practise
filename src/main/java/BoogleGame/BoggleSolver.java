package BoogleGame;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

public class BoggleSolver {
    Set<String> dictionary;

    // Initializes the data structure using the given array of strings as the dictionary.
    // (You can assume each word in the dictionary contains only the uppercase letters A through Z.)
    public BoggleSolver(String[] dictionaries) {
        dictionary = new HashSet<>();
        for (String s : dictionaries)
            dictionary.add(s);
    }

    // TODO : find all word which have character start from a cell x y in board use dfs
    // step 1 : find a word
    public void dfsBoard(BoggleBoard board, String str, int x, int y, boolean[][] visited) {
        int row = board.rows();
        int col = board.cols();

        visited[x][y] = true;
        str += board.getLetter(x, y);

        if (dictionary.contains(str))
            System.out.println(str);

        int minX = Math.max(x - 1, 0);
        int maxX = Math.min(col, x + 2);
        int minY = Math.max(y - 1, 0);
        int maxY = Math.min(row, y + 2);

        for (int i = minX; i < maxX; i++) {
            for (int j = minY; j < maxY; j++) {
                if (!visited[i][j]) {
                    dfsBoard(board, str, i, j, visited);
                }
            }
        }

        // backtracking
        str = str.substring(0, str.length() - 1);
        visited[x][y] = false;
    }


    // Returns the set of all valid words in the given Boggle board, as an Iterable.
    public Iterable<String> getAllValidWords(BoggleBoard board) {
        return null;
    }

    // Returns the score of the given word if it is in the dictionary, zero otherwise.
    // (You can assume the word contains only the uppercase letters A through Z.)
    public int scoreOf(String word) {
        return 0;
    }

    public static void main(String[] args) {
        String filename = "boggle/board4x4.txt";
        StdOut.println("4-by-4 board from file " + filename + ":");
        BoggleBoard board4 = new BoggleBoard(filename);
        StdOut.println(board4);

        String[] dictionary;
        String fileDictionary = "boggle/dictionary-small.txt";
        In in = new In(fileDictionary);
        dictionary = in.readAllStrings();


        BoggleSolver boggleSolver = new BoggleSolver(dictionary);
        boolean visited[][] = new boolean[board4.rows()][board4.cols()];
        boggleSolver.dfsBoard(board4, "", 0, 0, visited);
        StdOut.println();
    }
}
