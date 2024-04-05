package Algorithms_Part_1.Percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // nxn grid, true = is opened, false = is blocked
    private final boolean[][] grid;
    private final int size;
    private int nOpen;
    private WeightedQuickUnionUF unionUF;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n <= 0");
        // don't use row = 0, col = 0, use from [1 -> n], size = n
        grid = new boolean[n + 1][n + 1];
        this.size = n + 1;
        this.nOpen = 0;
        // [0 -> n-1]
        unionUF = new WeightedQuickUnionUF(size * size);
    }

    private int toIndex(int row, int col) {
        return row * this.size + col;
    }


    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row >= size || col <= 0 || col >= size)
            throw new IllegalArgumentException("row < 0 || row > size || col < 0 || col > size");

        grid[row][col] = true;
        nOpen++;

        // union with adj of a node if it is also opened
        int minY = Math.max(col - 1, 1);
        int maxY = Math.min(this.size - 1, col + 1);
        int minX = Math.max(row - 1, 1);
        int maxX = Math.min(this.size - 1, row + 1);

        for (int i = minX; i <= maxX; i++) {
            if (isOpen(i, col)) {
                int indexIJ = toIndex(i, col);
                int indexRowCol = toIndex(row, col);
                unionUF.union(indexIJ, indexRowCol);
            }
        }

        for (int i = minY; i <= maxY; i++) {
            if (isOpen(row, i)) {
                int indexIJ = toIndex(row, i);
                int indexRowCol = toIndex(row, col);
                unionUF.union(indexIJ, indexRowCol);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row >= size || col <= 0 || col >= size)
            throw new IllegalArgumentException("row < 0 || row > size || col < 0 || col > size");

        return grid[row][col] == true;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || row >= size || col <= 0 || col >= size)
            throw new IllegalArgumentException("row < 0 || row > size || col < 0 || col > size");

        for (int i = 1; i < this.size; i++) {
            if (isOpen(1, i)) {
                int indexColI = toIndex(1, i);
                int indexRowCol = toIndex(row, col);
                if (unionUF.find(indexColI) == unionUF.find(indexRowCol))
                    return true;
            }
        }

        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return nOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = 1; i < this.size; i++) {
            if (isOpen(this.size - 1, i) && isFull(this.size - 1, i)) {
                return true;
            }
        }

        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        WeightedQuickUnionUF unionUF1 = new WeightedQuickUnionUF(4);
        unionUF1.union(0, 2);

    }
}