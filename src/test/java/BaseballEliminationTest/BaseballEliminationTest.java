package BaseballEliminationTest;

import BaseballEliminationP.BaseballElimination;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class BaseballEliminationTest {

    @Test
    public void testReadFileInContructor_teams4() {
        BaseballElimination b = new BaseballElimination("baseball/teams4.txt");
        Assertions.assertEquals(4,b.numberOfTeams());
        Assertions.assertEquals(83,b.wins("Atlanta"));
        Assertions.assertEquals(79,b.losses("Philadelphia"));
        Assertions.assertEquals(3,b.remaining("Montreal"));
        Assertions.assertEquals(1,b.against("Atlanta", "Philadelphia"));
        Assertions.assertEquals(6,b.against("Atlanta", "New_York"));
    }

    @Test
    public void testEliminated_teams4() {
        BaseballElimination b = new BaseballElimination("baseball/teams4.txt");
        Assertions.assertTrue(b.isEliminated("Philadelphia"));
        Assertions.assertTrue(b.isEliminated("Montreal"));
        Assertions.assertFalse(b.isEliminated("Atlanta"));
        Assertions.assertFalse(b.isEliminated("New_York"));
    }

    @Test
    public void testEliminated_teams4a() {
        BaseballElimination b = new BaseballElimination("baseball/teams4a.txt");
        Assertions.assertFalse(b.isEliminated("CIA"));
        Assertions.assertTrue(b.isEliminated("Ghaddafi"));
        Assertions.assertTrue(b.isEliminated("Bin_Ladin"));
    }

    @Test
    public void testEliminated_teams5c() {
        BaseballElimination b = new BaseballElimination("baseball/teams5c.txt");
        Assertions.assertTrue(b.isEliminated("Philadelphia"));
    }

    @Test
    public void testCertificateOfElimination_teams5c() {
        BaseballElimination b = new BaseballElimination("baseball/teams5c.txt");
        Assertions.assertTrue(b.isEliminated("Philadelphia"));
        boolean found = false;
        for (String team : b.certificateOfElimination("Philadelphia")) {
            if (team.equals("Atlanta") || team.equals("Florida"))
                found = true;
        }
        Assertions.assertTrue(found);
    }

    @Test
    public void testEliminated_teams12() {
        BaseballElimination b = new BaseballElimination("baseball/teams12.txt");
        Assertions.assertTrue(b.isEliminated("Japan"));
    }



    @Test
    public void testCertificateOfElimination_teams4() {
        BaseballElimination b = new BaseballElimination("baseball/teams4.txt");
        //Assertions.assertTrue(b.isEliminated("Philadelphia"));
        boolean found = false;
        for (String team : b.certificateOfElimination("Philadelphia")) {
            if (team.equals("Atlanta") || team.equals("New_York"))
                found = true;
        }
        Assertions.assertTrue(found);
    }
}
