import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * @author Mayur Kulkarni <mayurkulkarni012@gmail.com>
 */
public class PercolationStats {
    private double[] openBoxes; // Stores the number of open boxes required to percolate
    private int trials;         // number of trials to perform

    public PercolationStats(int n, int trials) {
        this.trials = trials;
        openBoxes = new double[trials];
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                percolation.open(StdRandom.uniform(1, n + 1),
                        StdRandom.uniform(1, n + 1));
            }
            openBoxes[i] = percolation.numberOfOpenSites() / (double) (n * n);
        }
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats percolationStats = new PercolationStats(n, trials);
        System.out.printf("%-23s = %f%n", "mean", percolationStats.mean());
        System.out.printf("%-23s = %f%n", "stddev", percolationStats.stddev());
        System.out.printf("%-23s = [%f, %f]", "95% confidence interval",
                percolationStats.confidenceLo(), percolationStats.confidenceHi());
    }

    /**
     * Returns the means of a list
     *
     * @return mean of list
     */
    public double mean() {
        return StdStats.mean(openBoxes);
    }

    /**
     * Returns standard deviation of a list
     *
     * @return stddev
     */
    public double stddev() {
        return StdStats.stddev(openBoxes);
    }

    /**
     * Confidence interval low
     *
     * @return confidence interval low
     */
    public double confidenceLo() {
        return mean() - (1.96D * stddev()) / Math.sqrt(trials);
    }

    /**
     * Confidence interval high
     *
     * @return confidence interval high
     */
    public double confidenceHi() {
        return mean() + (1.96D * stddev()) / Math.sqrt(trials);
    }
}
