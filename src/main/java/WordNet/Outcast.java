package WordNet;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {
    private final WordNet wordNet;
    public Outcast(final WordNet wordnet)   {
        this.wordNet = wordnet;
    };

    // given an array of WordNet.WordNet nouns, return an outcast
    public String outcast(final String[] nouns) {
        int maxDis = Integer.MIN_VALUE;
        String result = "";
        for (int i = 0; i < nouns.length; i++) {
            int totalDis = 0;
            for (int j = 0; j < nouns.length; j++) {
                if (i != j)
                    totalDis += wordNet.distance(nouns[i], nouns[j]);
            }
            //System.out.println("total dis of word " + nouns[i] + " with others is " + totalDis);
            if(totalDis > maxDis) {
                maxDis = totalDis;
                result = nouns[i];
            }

        }
        return result;
    }
    public static void main(String[] argss){
        WordNet wordnet = new WordNet("wordnet/synsets.txt", "wordnet/hypernyms.txt");
        Outcast outcast = new Outcast(wordnet);
        String[] args = {"wordnet/outcast5.txt", "wordnet/outcast8.txt", "wordnet/outcast11.txt"};
        for (int t = 0; t < args.length; t++) {
            In in = new In(args[t]);
            String[] nouns = in.readAllStrings();
            StdOut.println(args[t] + ": " + outcast.outcast(nouns));
        }
    }
}
