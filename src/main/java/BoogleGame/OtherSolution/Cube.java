package BoogleGame.OtherSolution;

import java.util.ArrayList;
public class Cube {
    public char _letter = 0;
    public boolean _visited = false;
    public ArrayList<Cube> _neighbors = new ArrayList<Cube>();

    Cube(char letter_) {
        _letter = letter_;
    }
}