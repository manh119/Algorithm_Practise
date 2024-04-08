package org.example.Graph;

public class Number_of_Provinces {
    public int findCircleNum(int[][] isConnected) {
        boolean[] visited = new boolean[isConnected.length];
        int num = 0;
        for (int i = 0; i < isConnected.length; i++) {
            if (!visited[i]) {
                num++;
                dfs(i,visited, isConnected);
            }
        }
        return num;
    }

    //
    private void dfs(int i, boolean[] visited, int[][] isConnected) {
        visited[i] = true;
        for (int j = 0; j < isConnected.length; j++) {
            if (!visited[j] && isConnected[i][j] == 1)
                dfs(j, visited, isConnected);
        }
    }

    public static void main(String[] args) {
        Number_of_Provinces numberOfProvinces = new Number_of_Provinces();
        int[][] isConnected = {{1,0,0,1},{0,1,1,0},{0,1,1,1},{1,0,1,1}};
        System.out.println(numberOfProvinces.findCircleNum(isConnected));
    }


}
