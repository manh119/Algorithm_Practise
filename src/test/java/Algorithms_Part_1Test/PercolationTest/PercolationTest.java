package Algorithms_Part_1Test.PercolationTest;

import Algorithms_Part_1.Percolation.Percolation;
import edu.princeton.cs.algs4.In;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class PercolationTest {
    @Test
    public void testOpen_isOpen_input4() {
        In in = new In("percolation/input4.txt");      // input file
        int n = in.readInt();
        Percolation perc = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        // col 1 and 4 is open
        Assertions.assertTrue(perc.isOpen(4, 1));
        Assertions.assertTrue(perc.isOpen(3, 1));
        Assertions.assertTrue(perc.isOpen(2, 1));
        Assertions.assertTrue(perc.isOpen(1, 1));
        Assertions.assertTrue(perc.isOpen(1, 4));
        Assertions.assertTrue(perc.isOpen(2, 4));
        Assertions.assertTrue(perc.isOpen(3, 4));
        Assertions.assertTrue(perc.isOpen(4, 4));
        // col 2 and col 3 is not open
        Assertions.assertFalse(perc.isOpen(4, 2));
        Assertions.assertFalse(perc.isOpen(3, 2));
        Assertions.assertFalse(perc.isOpen(2, 2));
        Assertions.assertFalse(perc.isOpen(1, 2));
        Assertions.assertFalse(perc.isOpen(4, 3));
        Assertions.assertFalse(perc.isOpen(3, 3));
        Assertions.assertFalse(perc.isOpen(2, 3));
        Assertions.assertFalse(perc.isOpen(1, 3));
        // col 0 and row 0 is not use and thus throw exception
        Assertions.assertThrows(IllegalArgumentException.class, () -> perc.isOpen(0, 1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> perc.isOpen(0, 2));
        Assertions.assertThrows(IllegalArgumentException.class, () -> perc.isOpen(0, 3));
        Assertions.assertThrows(IllegalArgumentException.class, () -> perc.isOpen(0, 4));

        Assertions.assertThrows(IllegalArgumentException.class, () -> perc.isOpen(1, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> perc.isOpen(2, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> perc.isOpen(3, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> perc.isOpen(4, 0));

        Assertions.assertThrows(IllegalArgumentException.class, () -> perc.isOpen(0, 0));


    }

    @Test
    public void test_isFull_input4() {
        In in = new In("percolation/input4.txt");      // input file
        int n = in.readInt();
        Percolation perc = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        // col 1 and 4 is full
        Assertions.assertTrue(perc.isFull(4, 1));
        Assertions.assertTrue(perc.isFull(3, 1));
        Assertions.assertTrue(perc.isFull(2, 1));
        Assertions.assertTrue(perc.isFull(1, 1));
        Assertions.assertTrue(perc.isFull(1, 4));
        Assertions.assertTrue(perc.isFull(2, 4));
        Assertions.assertTrue(perc.isFull(3, 4));
        Assertions.assertTrue(perc.isFull(4, 4));

        // col 2 and col 3 is not full

        Assertions.assertFalse(perc.isFull(4, 2));
        Assertions.assertFalse(perc.isFull(3, 2));
        Assertions.assertFalse(perc.isFull(2, 2));
        Assertions.assertFalse(perc.isFull(1, 2));
        Assertions.assertFalse(perc.isFull(4, 3));
        Assertions.assertFalse(perc.isFull(3, 3));
        Assertions.assertFalse(perc.isFull(2, 3));
        Assertions.assertFalse(perc.isFull(1, 3));
    }

    @Test
    public void test_isFull_input3_no1() {
        In in = new In("percolation/input3-no1.txt");      // input file
        int n = in.readInt();
        Percolation perc = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        Assertions.assertFalse(perc.isFull(3, 1));
    }

    @Test
    public void test_isFull_input3_no2() {
        In in = new In("percolation/input3-no2.txt");      // input file
        int n = in.readInt();
        Percolation perc = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        Assertions.assertFalse(perc.isFull(3, 1));
    }

    @Test
    public void test_isFull_input3_no3() {
        In in = new In("percolation/input3-no3.txt");      // input file
        int n = in.readInt();
        Percolation perc = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        Assertions.assertFalse(perc.isFull(3, 1));
    }

    @Test
    public void testPercolation_input4() {
        In in = new In("percolation/input4.txt");      // input file
        int n = in.readInt();
        Percolation perc = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        Assertions.assertTrue(perc.percolates());
    }

    @Test
    public void testPercolation_input8_no() {
        In in = new In("percolation/input8-no.txt");      // input file
        int n = in.readInt();
        Percolation perc = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        Assertions.assertFalse(perc.percolates());
    }

    @Test
    public void testPercolation_input2_no() {
        In in = new In("percolation/input2-no.txt");      // input file
        int n = in.readInt();
        Percolation perc = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        Assertions.assertFalse(perc.percolates());
    }

    @Test
    public void testPercolation_input10() {
        In in = new In("percolation/input10.txt");      // input file
        int n = in.readInt();
        Percolation perc = new Percolation(n);
        while (!in.isEmpty()) {
            int i = in.readInt();
            int j = in.readInt();
            perc.open(i, j);
        }
        Assertions.assertFalse(perc.isFull(n, n - 1));
        Assertions.assertTrue(perc.isFull(n - 1, n));
        Assertions.assertTrue(perc.isFull(n, n));
        Assertions.assertTrue(perc.percolates());
    }


}