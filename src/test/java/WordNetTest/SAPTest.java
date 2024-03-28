package WordNetTest;
import WordNet.SAP;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class SAPTest {

    @Test
    public void testLength() {
        In inDigrap1 = new In("wordnet/digraph1.txt");
        Digraph G = new Digraph(inDigrap1);
        SAP sap = new SAP(G);
        int actualLength = sap.length(3,11);
        int expectLength = 4;
        Assert.assertEquals(expectLength, actualLength);
    }

    // Test 16: check length() and ancestor() with iterable arguments
    @Test
    public void testLength2() {
        In inDigrap1 = new In("wordnet/digraph-wordnet.txt");
        Digraph G = new Digraph(inDigrap1);
        SAP sap = new SAP(G);
        int actualLength = sap.length(58962,27758);
        int expectLength = 12;
        Assert.assertEquals(expectLength, actualLength);
    }

    @Test
    public void testLength3() {
        In inDigrap1 = new In("wordnet/digraph-wordnet.txt");
        Digraph G = new Digraph(inDigrap1);
        SAP sap = new SAP(G);
        List<Integer> v = List.of(22618);
        List<Integer> w = List.of(1323, 76352);

        int actualLength = sap.length(v,w);
        int expectLength = 17;
        Assert.assertEquals(expectLength, actualLength);
    }

    @Test
    public void testLength4() {
        In inDigrap1 = new In("wordnet/digraph-wordnet.txt");
        Digraph G = new Digraph(inDigrap1);
        SAP sap = new SAP(G);
        List<Integer> v = List.of(40692, 53822, 66763);
        List<Integer> w = List.of(563, 9065, 9209, 10484, 11248, 14475, 38148, 45678, 58277, 59757, 61220);

        int actualLength = sap.length(v,w);
        int expectLength = 10;
        Assert.assertEquals(expectLength, actualLength);
    }

    @Test
    public void timming() {}




}
