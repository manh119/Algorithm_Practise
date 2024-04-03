package BoogleGame.OtherSolution;

import java.util.HashMap;
import java.util.Map;

public class Node {
    public boolean _finished = false;
    private HashMap<Character, Node> _next = new HashMap<Character, Node>();

    public void addSuffix(String suffix_) {
        if (suffix_.isEmpty()) {
            _finished = true;
        } else {
            char c = suffix_.charAt(0);
            Node n = _next.get(c);
            if (n == null) {
                n = new Node();
                _next.put(c, n);
            }
            n.addSuffix(suffix_.substring(1));
        }
    }

    Node get(char c_) {
        return (_next.get(c_));
    }

    void printAll(String prefix_) {
        if (_finished) {
            System.out.println(prefix_);
        }
        for (Map.Entry<Character, Node> n : _next.entrySet()) {
            n.getValue().printAll(prefix_ + n.getKey());
        }
    }
}

