package BaseballEliminationP;

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;

public class BaseballElimination {
    private final int nTeams;
    private final List<String> nameTeams;

    private final int[] wins;

    private final int[] loss;

    private final int[] remain;

    private final int[][] against;

    private List<String> certificateOfElimination;


    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {
        if (filename == null)
            throw new IllegalArgumentException("File name should not be null");
        In in = new In(filename);
        nTeams = in.readInt();
        nameTeams = new ArrayList<>();
        wins = new int[nTeams];
        loss = new int[nTeams];
        remain = new int[nTeams];
        against = new int[nTeams][nTeams];
        readInputFile(in);

    }

    private void readInputFile(In in) {
        for (int i = 0; i < nTeams; i++) {
            String name = in.readString();
            nameTeams.add(i, name);
            wins[i] = in.readInt();
            loss[i] = in.readInt();
            remain[i] = in.readInt();
            for (int j = 0; j < nTeams; j++) {
                against[i][j] = in.readInt();
            }
        }
    }

    // number of teams
    public int numberOfTeams() {
        return nTeams;
    }

    // all teams
    public Iterable<String> teams() {
        return new ArrayList<>(nameTeams);
    }

    // number of wins for given team
    public int wins(String team) {
        if (team == null || !nameTeams.contains(team))
            throw new IllegalArgumentException("Invalid team parameter");
        return this.wins[nameTeams.indexOf(team)];
    }

    // number of losses for given team
    public int losses(String team) {
        if (team == null || !nameTeams.contains(team))
            throw new IllegalArgumentException("Invalid team parameter");
        return this.loss[nameTeams.indexOf(team)];
    }
    // number of remaining games for given team

    public int remaining(String team) {
        if (team == null || !nameTeams.contains(team))
            throw new IllegalArgumentException("Invalid team parameter");
        return this.remain[nameTeams.indexOf(team)];
    }
    // number of remaining games between team1 and team2

    public int against(String team1, String team2) {
        if (team1 == null || !nameTeams.contains(team1) || team2 == null || !nameTeams.contains(team2))
            throw new IllegalArgumentException("Invalid team parameter");
        int index1 = nameTeams.indexOf(team1);
        int index2 = nameTeams.indexOf(team2);
        return this.against[index1][index2];
    }

    // is given team eliminated?

    public boolean isEliminated(String team) {
        if (team == null || !nameTeams.contains(team))
            throw new IllegalArgumentException("Invalid team parameter");
        certificateOfElimination = new ArrayList<>();

        int x = nameTeams.indexOf(team);

        // trivial - case
        for (int i = 0; i < nTeams; i++) {
            if (i != x && wins[x] + remain[x] < wins[i]) {
                certificateOfElimination.add(nameTeams.get(i));
                return true;
            }
        }

        // nontrivial - case
        // FlowNetwork
        int numGameVirtices = (nTeams - 1) * (nTeams - 2) / 2;
        int t = numGameVirtices + nTeams + 1;
        int s = t - 1;
        FlowNetwork flowNetwork = getFlowNetwork(t, x, s);

        // total game possible in = (S \ x)
        int totalRemain = totalRemain(x);

        // FordFulkerson
        FordFulkerson fordFulkerson = new FordFulkerson(flowNetwork, s, t);

        if (fordFulkerson.value() < totalRemain) {
            // calculate certificateOfElimination
            for (int i = 0; i < nTeams; i++) {
                if (i != x && fordFulkerson.inCut(i)) {
                    certificateOfElimination.add(nameTeams.get(i));
                }
            }
            return true;
        }

        return false;
    }

    private FlowNetwork getFlowNetwork(int t, int x, int s) {
        FlowNetwork flowNetwork = new FlowNetwork(t + 1);

        // TODO : s -> game vertices
        // TODO : game vertices -> team vertices
        int gameVertice = nTeams;
        for (int i = 0; i < nTeams; i++) {
            for (int j = i + 1; j < nTeams; j++ ) {
                if (i != x && j != x) {
                    FlowEdge sToGameVertices = new FlowEdge(s, gameVertice, against[i][j]);
                    FlowEdge gameVerticesToTeami = new FlowEdge(gameVertice, i, Double.POSITIVE_INFINITY);
                    FlowEdge gameVerticesToTeamj = new FlowEdge(gameVertice, j, Double.POSITIVE_INFINITY);

                    flowNetwork.addEdge(sToGameVertices);
                    flowNetwork.addEdge(gameVerticesToTeami);
                    flowNetwork.addEdge(gameVerticesToTeamj);
                    gameVertice++;
                }
            }
        }

        // team vertices -> t
        for (int i = 0; i < nTeams; i++) {
            if (i != x) {
                FlowEdge flowEdge = new FlowEdge(i, t, wins[x] + remain[x] - wins[i]);
                flowNetwork.addEdge(flowEdge);
            }
        }
        return flowNetwork;
    }

    // total game possible in Tij = (S \ x)
    private int totalRemain(int x) {
        int totalT = 0;
        for (int i = 0; i < nTeams; i++) {
            for (int j = i + 1; j < nTeams; j++) {
                if (i != x && j != x)
                    totalT += against[i][j];
            }
        }
        return totalT;
    }


    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        if (team == null || !nameTeams.contains(team))
            throw new IllegalArgumentException("Invalid team parameter");
        this.isEliminated(team);
        if (certificateOfElimination.isEmpty()) return null;
        return new ArrayList<>(certificateOfElimination);
    }

    public static void main(String[] args) {
        BaseballElimination b = new BaseballElimination("baseball/teams5c.txt");
        b.isEliminated("Philadelphia");
    }

}