package BoogleGame.OtherSolution;

import edu.princeton.cs.algs4.In;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

public class MyBenchmark {

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.SECONDS)
    @Fork(value = 1)
    @Warmup(iterations = 1)
    @Measurement(iterations = 1)
    public void measureSolveBoard() {
        String file_dic = "boggle/dictionary-yawl.txt";
        Node dictionaryRoot = new Node();

        In in = new In(file_dic);
        String[] dic = in.readAllStrings();
        for (String d : dic)
            dictionaryRoot.addSuffix(d);

        String letters = "ATVEARTGODRDSEIEHRSSIEESNALNEDNMSITI";

        Board board = new Board(letters);

        for (String word : board.solve(dictionaryRoot)) {
            System.out.println("(" + word.length() + ") " + word);
        }

        System.out.println("[ OK ] Solved");
    }


}
