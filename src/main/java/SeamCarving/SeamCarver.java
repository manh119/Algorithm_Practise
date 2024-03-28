package SeamCarving;

import edu.princeton.cs.algs4.Picture;

import java.awt.Color;
import java.util.Arrays;

// TODO : rewrite all - current code is good
// TODO : Give the worst-case running time to remove r rows and c columns from a width-by-height image as a function of r, c, width, and height
public class SeamCarver {
    private Picture picture;
    private int width;
    private int height;

    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null)
            throw new IllegalArgumentException("Picture should not be null value");
        this.picture = new Picture(picture);
        width = picture.width();
        height = picture.height();
    }

    // current picture
    public Picture picture() {
        return new Picture(this.picture);
    }

    // energy of pixel at column x and row y


    public double energy(int x, int y) {
        return energyPic(x,y,this.picture);
    }

    private double energyPic(int x, int y, Picture pic) {
        int width = pic.width();
        int height = pic.height();
        if (x < 0 || y < 0 || x > width - 1 || y > height - 1) {
            throw new IllegalArgumentException();
        }

        if (x == 0 || y == 0 || x == width - 1 || y == height - 1) {
            return 1000;
        }

        Color top = pic.get(x, y + 1);
        Color bottom = pic.get(x, y - 1);
        Color left = pic.get(x - 1, y);
        Color right = pic.get(x + 1, y);

        return Math.sqrt(squareGradient(top, bottom) + squareGradient(left, right));
    }

    private double squareGradient(Color first, Color second) {
        return Math.pow(first.getRed() - second.getRed(), 2) +
                Math.pow(first.getGreen() - second.getGreen(), 2) +
                Math.pow(first.getBlue() - second.getBlue(), 2);
    }


    // TODO : instance object itself in its class ???
    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        Picture transposed = new Picture(picture.height(), picture.width());
        for (int x = 0; x < picture.width(); x++) {
            for (int y = 0; y < picture.height(); y++) {
                transposed.setRGB(y, x, picture.getRGB(x, y));
            }
        }
        return findVerticalSeamAPic(transposed);
    }

    public int[] findVerticalSeam() {
        return findVerticalSeamAPic(this.picture);
    }


    // findVertical seam of a picture
    private int[] findVerticalSeamAPic(Picture pic) {
        int width = pic.width();
        int height = pic.height();
        double[][] distTo = new double[width][height];
        int[][] edgeTo = new int[width][height];

        // Initialize distance array
        for (int x = 0; x < width; x++) {
            Arrays.fill(distTo[x], Double.POSITIVE_INFINITY);
        }

        // calculate energy
        double[][] energies = new double[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                energies[i][j] = energyPic(i, j, pic);
            }
        }

        // Initialize the top row of distTo with energies
        for (int x = 0; x < width; x++) {
            distTo[x][0] = energies[x][0];
        }

        // Topological sort
        for (int y = 0; y < height - 1; y++) {
            for (int x = 0; x < width; x++) {
                relax(x, y, distTo, edgeTo, energies);
            }
        }

        // Find the minimum seam
        int minX = 0;
        for (int x = 1; x < width; x++) {
            if (distTo[x][height - 1] < distTo[minX][height - 1]) {
                minX = x;
            }
        }

        // Reconstruct seam from edgeTo
        int[] seam = new int[height];
        for (int y = height - 1; y >= 0; y--) {
            seam[y] = minX;
            minX = edgeTo[minX][y];
        }
        return seam;
    }

    private void relax(int x, int y, double[][] distTo, int[][] edgeTo, double[][] energy) {
        for (int dx = -1; dx <= 1; dx++) {
            int nextX = x + dx;
            if (nextX >= 0 && nextX < distTo.length) {
                if (distTo[x][y] + energy[nextX][y + 1] < distTo[nextX][y + 1]) {
                    distTo[nextX][y + 1] = distTo[x][y] + energy[nextX][y + 1];
                    edgeTo[nextX][y + 1] = x;
                }
            }
        }
    }


    public int width() {
        return width;
    }

    // height of current picture
    public int height() {
        return height;
    }


    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null || this.height() <= 1 || seam.length != this.width()) {
            throw new IllegalArgumentException();
        }

        Picture newPicture = new Picture(this.width(), this.height() - 1);

        int prevSeam = seam[0];

        for (int x = 0; x < this.width(); x++) {
            if (Math.abs(seam[x] - prevSeam) > 1 || seam[x] < 0 || seam[x] >= this.height()) {
                throw new IllegalArgumentException();
            }
            prevSeam = seam[x];

            for (int y = 0; y < this.height(); y++) {
                if (seam[x] == y) continue;

                Color color = this.picture.get(x, y);
                newPicture.set(x, seam[x] > y ? y : y - 1, color);
            }
        }

        this.picture = newPicture;
        this.height = newPicture.height();
        this.width = newPicture.width();
    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null || this.width() <= 1 || seam.length != this.height()) {
            throw new IllegalArgumentException();
        }

        Picture newPicture = new Picture(this.width() - 1, this.height());

        int prevSeam = seam[0];

        for (int y = 0; y < this.height(); y++) {
            if (Math.abs(seam[y] - prevSeam) > 1 || seam[y] < 0 || seam[y] >= this.width()) {
                throw new IllegalArgumentException();
            }
            prevSeam = seam[y];

            for (int x = 0; x < this.width(); x++) {
                if (seam[y] == x) continue;

                Color color = this.picture.get(x, y);
                newPicture.set(seam[y] > x ? x : x - 1, y, color);
            }
        }

        this.picture = newPicture;
        this.height = newPicture.height();
        this.width = newPicture.width();
    }

    //  unit testing (optional)
    public static void main(String[] args) {
        SeamCarver seam;
        Picture picture = new Picture("seam/6x5.png");
        seam = new SeamCarver(picture);
        seam.findVerticalSeam();
    }

}
