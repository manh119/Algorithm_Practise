package Algorithms_Part_1.Percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    // nxn grid, true = is opened, false = is blocked
    private final int topRoot;
    private final int bottomRoot;
    private final boolean[][] grid;
    private final int size;
    private int nOpen;
    private final WeightedQuickUnionUF unionUF;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("n <= 0");
        // don't use row = 0, col = 0, use from [1 -> n], size = n
        grid = new boolean[n + 1][n + 1];
        this.size = n + 1;
        this.nOpen = 0;
        // [0 -> n-1]
        unionUF = new WeightedQuickUnionUF(size * size + 2);
        topRoot = size * size;
        bottomRoot = size * size + 1;
    }

    private int toIndex(int row, int col) {
        return row * this.size + col;
    }


    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row >= size || col <= 0 || col >= size)
            throw new IllegalArgumentException("row < 0 || row > size || col < 0 || col > size");

        if (isOpen(row, col))
            return;
        grid[row][col] = true;
        nOpen++;

        int indexRowCol = toIndex(row, col);

        // top
        if (row == 1)
            unionUF.union(topRoot, indexRowCol);
        else if (isOpen(row - 1, col)) {
            unionUF.union(toIndex(row - 1, col), indexRowCol);
        }
        // bottom
        if (row == this.size - 1)
            unionUF.union(bottomRoot, indexRowCol);
        else if (isOpen(row + 1, col)) {
            unionUF.union(toIndex(row + 1, col), indexRowCol);
        }
        // left
        if (col > 1 && isOpen(row, col - 1))
            unionUF.union(toIndex(row, col - 1), indexRowCol);

        // right
        if (col < this.size - 1 && isOpen(row, col + 1))
            unionUF.union(toIndex(row, col + 1), indexRowCol);


    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row >= size || col <= 0 || col >= size)
            throw new IllegalArgumentException("row < 0 || row > size || col < 0 || col > size");

        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || row >= size || col <= 0 || col >= size)
            throw new IllegalArgumentException("row < 0 || row > size || col < 0 || col > size");
        int indexRowCol = toIndex(row, col);
        return unionUF.find(topRoot) == unionUF.find(indexRowCol);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return nOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        return (unionUF.find(topRoot) == unionUF.find(bottomRoot));
    }

    // test client (optional)
    public static void main(String[] args) {
        WeightedQuickUnionUF unionUF1 = new WeightedQuickUnionUF(4);
        unionUF1.union(0, 2);

    }
}