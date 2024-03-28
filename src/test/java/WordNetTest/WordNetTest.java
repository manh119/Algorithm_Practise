package WordNetTest;
import WordNet.SAP;
import WordNet.WordNet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import junit.framework.AssertionFailedError;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordNetTest {
    WordNet w;
    WordNet w1;
    @Before
    public void readWordNet() {
        w = new WordNet("wordnet/synsets.txt", "wordnet/hypernyms.txt");
    }
    // Test 4: check sap() with random noun pairs
    @Test
    public void testSap() {
        String actualString = w.sap("donkeywork", "cephalic_index");
        String expectString = "abstraction abstract_entity";

        Assert.assertEquals(expectString, actualString);
    }

    //  * 1000 pairs using synsets = synsets1000-subgraph.txt; hypernyms = hypernyms1000-subgraph.txt
    @Test
    public void testSap2() {
        String actualString = w.sap("cassava_starch", "precipitin");
        String expectString = "macromolecule supermolecule";

        Assert.assertEquals(expectString, actualString);
    }

    @Test
    public void testValidRootException() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> w1 = new WordNet("wordnet/synsets3.txt",
                        "wordnet/hypernyms3InvalidTwoRoots.txt"));

    }

    @Test
    public void hypernyms3InvalidCycle() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> w1 = new WordNet("wordnet/synsets3.txt",
                        "wordnet/hypernyms3InvalidCycle.txt"));

    }

}
