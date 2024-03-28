package SeamCarvingTest;

import SeamCarving.SeamCarver;
import edu.princeton.cs.algs4.Picture;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

public class SeamCarverTest {

    @Test
    public void testEnergy1_3x4() {
        SeamCarver seam = new SeamCarver(new Picture("seam/3x4.png"));
        double actualValue = seam.energy(1, 1);
        double expectedValue = Math.sqrt(52225);
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testEnergy2_3x4() {
        SeamCarver seam = new SeamCarver(new Picture("seam/3x4.png"));
        double actualValue = seam.energy(1, 2);
        double expectedValue = Math.sqrt(52024);
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testEnergyConer1_3x4() {
        SeamCarver seam = new SeamCarver(new Picture("seam/3x4.png"));
        double actualValue = seam.energy(0, 0);
        double expectedValue = 1000;
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testEnergyConer2_3x4() {
        SeamCarver seam = new SeamCarver(new Picture("seam/3x4.png"));
        double actualValue = seam.energy(2, 2);
        double expectedValue = 1000;
        Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testFindVerticalSeam_6x5() {
        SeamCarver seam = new SeamCarver(new Picture("seam/6x5.png"));
        int[] actualValue = seam.findVerticalSeam();
        int[][] expectedValues = {
                {3, 4, 3, 2, 2},
                {4, 4, 3, 2, 2},
                {5, 4, 3, 2, 2},
                {3, 4, 3, 2, 1},
                {4, 4, 3, 2, 1},
                {5, 4, 3, 2, 1},
                {3, 4, 3, 2, 3},
                {4, 4, 3, 2, 3},
                {5, 4, 3, 2, 3}
        };

        boolean found = false;
        for (int[] expectedValue : expectedValues) {
            if (Arrays.equals(actualValue, expectedValue)) {
                found = true;
                break;
            }
        }
        Assertions.assertTrue(found);
    }

    @Test
    public void testFindVerticalSeam_12x10() {
        SeamCarver seam2;
        seam2 = new SeamCarver(new Picture("seam/12x10.png"));
        int[] actualValue = seam2.findVerticalSeam();
        int[][] expectedValues = {
                {6, 7, 7, 6, 6, 7, 7, 7, 8, 7}
        };

        boolean found = false;
        for (int[] expectedValue : expectedValues) {
            if (Arrays.equals(actualValue, expectedValue)) {
                found = true;
                break;
            }
        }
        Assertions.assertTrue(found);
    }

    @Test
    public void testFindVerticalSeam_7x10() {
        SeamCarver seam;
        seam = new SeamCarver(new Picture("seam/7x10.png"));
        int[] actualValue = seam.findVerticalSeam();
        int[][] expectedValues = {
                {2, 3, 4, 3, 4, 3, 3, 2, 2, 1}
        };

        boolean found = false;
        for (int[] expectedValue : expectedValues) {
            if (Arrays.equals(actualValue, expectedValue)) {
                found = true;
                break;
            }
        }
        Assertions.assertTrue(found);
    }

    @Test
    public void testFindVerticalSeam_10x12() {
        SeamCarver seam2;
        seam2 = new SeamCarver(new Picture("seam/10x12.png"));
        int[] actualValue = seam2.findVerticalSeam();
        int[][] expectedValues = {
                {5, 6, 7, 8, 7, 7, 6, 7, 6, 5, 6, 5}
        };

        boolean found = false;
        for (int[] expectedValue : expectedValues) {
            if (Arrays.equals(actualValue, expectedValue)) {
                found = true;
                break;
            }
        }
        Assertions.assertTrue(found);
    }

    @Test
    public void testFindHorizontalSeam_6x5() {
        SeamCarver seam = new SeamCarver(new Picture("seam/6x5.png"));
        int[] actualValue = seam.findHorizontalSeam();
        int[] expectedValues = {1, 2, 1, 2, 1, 0};       ;

        Assertions.assertArrayEquals(expectedValues, actualValue);
    }

    @Test
    public void testFindHorizontalSeam_10x12() {
        SeamCarver seam2;
        seam2 = new SeamCarver(new Picture("seam/10x12.png"));
        int[] actualValue = seam2.findHorizontalSeam();
        int[] expectedValues = {8, 9, 10, 10, 10, 9, 10, 10, 9, 8};

        Assertions.assertArrayEquals(expectedValues, actualValue);
    }
}
