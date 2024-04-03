package BoogleGame.OtherSolution;

import java.util.ArrayList;
import java.util.HashSet;

public class Board {
    private ArrayList<Cube> _cubes = new ArrayList<Cube>();
    private int _size = 0;

    public Board(String letters_) {
        int len = (int) Math.sqrt((float) letters_.length());
        assert (len * len == letters_.length());
        _size = len;
        for (char l : letters_.toCharArray()) {
            _cubes.add(new Cube(l));
        }
        int[][] deltas = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int x = 0; x < len; ++x) {
            for (int y = 0; y < len; ++y) {
                for (int[] d : deltas) {
                    int nx = x + d[0];
                    int ny = y + d[1];
                    if ((nx >= 0) && (nx < len) && (ny >= 0) && (ny < len)) {
                        get_cube(x, y)._neighbors.add(get_cube(nx, ny));
                    }
                }
            }
        }
    }

    public Cube get_cube(int x_, int y_) {
        return (_cubes.get(y_ * _size + x_));
    }

    public ArrayList<String> solve(Node dictionary_) {
        HashSet<String> result = new HashSet<String>();
        for (Cube cube : _cubes) {
            solveRecursive(result, "", cube, dictionary_);
        }
        ArrayList<String> resultSorted = new ArrayList<String>(result);
        java.util.Collections.sort(
                resultSorted,
                new java.util.Comparator<String>() {
                    public int compare(String s1, String s2) {
                        return s1.length() - s2.length();
                    }
                }
        );
        return (resultSorted);
    }

    void solveRecursive(HashSet<String> result, String prefix_, Cube cube_, Node dictNode_) {
        Node nextNode = dictNode_.get(cube_._letter);
        if (nextNode == null) {
            return;
        }
        cube_._visited = true;
        String newPrefix = prefix_ + cube_._letter;
        if (nextNode._finished && (newPrefix.length() >= 3)) {
            result.add(newPrefix);
        }
        for (Cube neighbor : cube_._neighbors) {
            if (!neighbor._visited) {
                solveRecursive(result, newPrefix, neighbor, nextNode);
            }
        }
        cube_._visited = false;
    }
}
