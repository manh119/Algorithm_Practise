package WordNet;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


public class WordNet {
    private final HashMap<String, Set<Integer>> map_name_setId;
    private final HashMap<Integer, String> map_id_Names;
    private final SAP sap;
    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        map_id_Names = new HashMap<>();
        map_name_setId = new HashMap<>();
        In syssetsInput = new In(synsets);
        In hypernymsInput = new In(hypernyms);

        while (syssetsInput.hasNextLine()) {
            String line = syssetsInput.readLine();
            String[] s = line.split(",", 0);
            int id = Integer.parseInt(s[0]);
            String list_name = s[1];
            // TODO : build 2 map
            String[] names = list_name.split(" ", 0);
            map_id_Names.put(id,list_name);
            //
            for (String name : names) {
                Set<Integer> set_id = map_name_setId.getOrDefault(name, new HashSet<>());
                set_id.add(id);
                map_name_setId.put(name, set_id);
            }
        }
//        System.out.println("-----------");
//        System.out.println(map_id_setName.toString());
//        System.out.println("-----------");
//        System.out.println(map_name_setId.toString());

        // TODO : read input to hashMap
        HashMap<Integer, Set<Integer>> map_hypernyms = new HashMap<>();
        while(hypernymsInput.hasNextLine()) {
            String line = hypernymsInput.readLine();
            String[] nums = line.split(",", 0);
            int id = Integer.parseInt(nums[0]);
            Set<Integer> setHyper = new HashSet<>();
            for(int i = 1; i < nums.length; i++)
                setHyper.add(Integer.parseInt(nums[i]));
            map_hypernyms.put(id, setHyper);
        }
//        System.out.println("-----------");
//        System.out.println(map_hypernyms.toString());

        // TODO : build graph from hashMap
        Digraph G = new Digraph(map_hypernyms.size());
        for(int i = 0; i < G.V(); i++) {
            Set<Integer> setE = map_hypernyms.get(i);
            if (setE == null)
                throw new IllegalArgumentException("graph have more than one root");
            for (Integer e : setE)
                G.addEdge(i, e);
        }
        // check cycle
        DirectedCycle dc = new DirectedCycle(G);
        if (dc.hasCycle())
            throw new IllegalArgumentException("Graph should not have cycle");
        checkRoot(G);

//        System.out.println("------------");
//        System.out.println(G.toString());

        sap = new SAP(G);
    }

    private void checkRoot(Digraph dag) {
        int roots = 0;
        for (int i = 0; i < dag.V(); i++) {
            if (dag.outdegree(i) == 0) {
                roots++;
            }
        }

        if (roots != 1) {
            throw new IllegalArgumentException();
        }
    }

    // returns all WordNet.WordNet nouns
    public Iterable<String> nouns() {
        return map_name_setId.keySet();
    }

    // is the word a WordNet.WordNet noun?
    public boolean isNoun(String word) {
        if (word == null)
            throw new IllegalArgumentException("parameter should not be null");
        return map_name_setId.containsKey(word);
    }

    // distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        if(!isNoun(nounA) || !isNoun(nounB))
            throw new IllegalArgumentException("nounA or nounB is not noun word");
        Set<Integer> setIdNounA = map_name_setId.get(nounA);
        Set<Integer> setIdNounB = map_name_setId.get(nounB);
        return sap.length(setIdNounA, setIdNounB);
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        if(!isNoun(nounA) || !isNoun(nounB))
            throw new IllegalArgumentException("nounA or nounB is not noun word");
        Set<Integer> setIdNounA = map_name_setId.get(nounA);
        Set<Integer> setIdNounB = map_name_setId.get(nounB);
        int id_result = sap.ancestor(setIdNounA, setIdNounB);
        String names =  map_id_Names.get(id_result);
        return names;
    }

    // do unit testing of this class
    public static void main(String[] args) {
        WordNet wordNet = new WordNet("wordnet/synsets.txt", "wordnet/hypernyms.txt");
        System.out.println(wordNet.distance("edible_fruit", "physical_entity"));
    }
}