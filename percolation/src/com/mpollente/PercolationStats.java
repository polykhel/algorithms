/* *****************************************************************************
 *  Name:              Michael Pollente
 *  Coursera User ID:  9ff155042d2ef2647ddfa01b9ff5d012
 *  Last modified:     04/17/2020
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int trials;
    private double[] percolationThreshold;

    PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.trials = trials;
        percolationThreshold = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                p.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
            }
            percolationThreshold[i] = (double) p.numberOfOpenSites() / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(percolationThreshold);
    }

    public double stddev() {
        return StdStats.stddev(percolationThreshold);
    }

    public double confidenceLow() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(trials));
    }

    public double confidenceHigh() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(trials));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, trials);
        System.out.printf("%-25s= %f\n", "mean", percolationStats.mean());
        System.out.printf("%-25s= %f\n", "stddev", percolationStats.stddev());
        System.out.printf("%-25s= [%f, %f]", "95% confidence interval",
                          percolationStats.confidenceLow(),
                          percolationStats.confidenceHigh());
    }
}
