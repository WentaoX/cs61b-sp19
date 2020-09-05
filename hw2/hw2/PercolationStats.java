package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
import java.lang.Math;

public class PercolationStats {

    private double[] openRatio;
    private int gridsN;
    private Percolation pcl;
    private int T;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N < 0 || T < 0) {
            throw new java.lang.IllegalArgumentException("N or T cannot be less than 0");
        }
        this.T = T;
        openRatio = new double[T];
        gridsN = N * N;


        for (int iteration = 0; iteration < T; iteration++){
            pcl = pf.make(N);
            while (!pcl.percolates()) {
                int pRow = StdRandom.uniform(N);
                int pCol = StdRandom.uniform(N);
//                System.out.println("" + pRow + "" + pCol);

                if (!pcl.isOpen(pRow, pCol)) {
                    pcl.open(pRow, pCol);
                }
            }
            openRatio[iteration] = (float) pcl.numberOfOpenSites()/gridsN;
        }
    }
    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(openRatio);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(openRatio);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean() - 1.96*stddev()/Math.sqrt(this.T);
    }
    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean() + 1.96*stddev()/Math.sqrt(this.T);
        }
    }
