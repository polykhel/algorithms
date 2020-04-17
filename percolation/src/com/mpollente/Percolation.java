/* *****************************************************************************
 *  Name:              Michael Pollente
 *  Coursera User ID:  9ff155042d2ef2647ddfa01b9ff5d012
 *  Last modified:     04/15/2020
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int size; // Percolation system size
    private boolean[] open; // Percolation system
    private int openSites; // number of open sites
    private WeightedQuickUnionUF unionFind;
    private WeightedQuickUnionUF unionFindForBackWash;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        size = n;
        open = new boolean[n * n + 2];
        openSites = 0;
        unionFind = new WeightedQuickUnionUF(n * n + 2);
        unionFindForBackWash = new WeightedQuickUnionUF(n * n + 1);
        for (int i = 1; i <= n; i++) {
            unionFind.union(encode(1, i), 0);
            unionFindForBackWash.union(encode(1, i), 0);
            unionFind.union(encode(n, i), n * n + 1);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateIndices(row, col);
        if (!isOpen(row, col)) {
            open[encode(row, col)] = true;
            openSites += 1;
            // check north neighbor
            if (row > 1 && isOpen(row - 1, col)) {
                unionFind.union(encode(row, col), encode(row - 1, col));
                unionFindForBackWash.union(encode(row, col), encode(row - 1, col));
            }
            // check west neighbor
            if (col > 1 && isOpen(row, col - 1)) {
                unionFind.union(encode(row, col), encode(row, col - 1));
                unionFindForBackWash.union(encode(row, col), encode(row, col - 1));
            }
            // check south neighbor
            if (row < size && isOpen(row + 1, col)) {
                unionFind.union(encode(row, col), encode(row + 1, col));
                unionFindForBackWash.union(encode(row, col), encode(row + 1, col));
            }
            // check east neighbor
            if (col < size && isOpen(row, col + 1)) {
                unionFind.union(encode(row, col), encode(row, col + 1));
                unionFindForBackWash.union(encode(row, col), encode(row, col + 1));
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateIndices(row, col);
        return open[encode(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateIndices(row, col);
        return isOpen(row, col)
                && unionFindForBackWash.find(encode(row, col)) == unionFindForBackWash.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return unionFind.find(0) == unionFind.find(size * size + 1);
    }

    private int encode(int row, int col) {
        validateIndices(row, col);
        return (size * (row - 1) + col);
    }

    private void validateIndices(int i, int j) {
        if (i <= 0 || i > size || j <= 0 || j > size)
            throw new IllegalArgumentException("index " + i + " is not between 1 and " + size);
    }


    public static void main(String[] args) {
        Percolation p = new Percolation(Integer.parseInt(args[0]));
        p.open(1, 2);
        p.open(2, 2);
        p.open(2, 3);
        p.open(3, 1);
        p.open(3, 3);
        System.out.println(p.isFull(3, 1));
        System.out.println(p.percolates());
    }
}
