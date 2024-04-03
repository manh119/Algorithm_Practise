package BoogleGame.OtherSolution;

/*
 * BENCH_BUILD_CMD:javac solve.java
 * BENCH_INVOKE_CMD:java solve ./dict.txt
 * BENCH_VERSION_CMD:java -version 2>&1 | awk '{print $3;exit}'
 */

import edu.princeton.cs.algs4.In;

public class solve {
    public static void main(String[] argv) {
        String file_dic = "boggle/dictionary-yawl.txt";
        Node dictionaryRoot = new Node();

        System.out.println("[>] Loading dictionary from file: " + file_dic);
        In in = new In(file_dic);
        String[] dic = in.readAllStrings();
        for (String d : dic)
            dictionaryRoot.addSuffix(d);

        System.out.println("[ OK ] Solving");
        String letters = "ATVEAR" +
                "TGODRD" +
                "SEIEHR" +
                "SSIEES" +
                "NALNED" +
                "NMSITI";

        Board board = new Board(letters);

        for (String word : board.solve(dictionaryRoot)) {
            System.out.println("(" + word.length() + ") " + word);
        }

        System.out.println("[ OK ] Solved");

    }
}

/* vim: set ft=java: */
