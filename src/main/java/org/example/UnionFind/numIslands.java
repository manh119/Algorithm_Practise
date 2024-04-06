package org.example.UnionFind;


public class numIslands {
    public int numIslands(char[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == '1')
                    count++;
                dfs(grid, i, j);
            }
        }
        return count;
    }

    // '0' as visited, '1' as unvisited
    private void dfs(char[][] grid, int i, int j) {
        int row = grid.length;
        int col = grid[0].length;

        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] == '0')
            return;

        grid[i][j] = '0';

        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }

}
