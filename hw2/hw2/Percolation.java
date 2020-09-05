package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.algs4.QuickFindUF;

import java.util.Arrays;


public class Percolation {
    private int[][] grids;
    private QuickFindUF uf;
    private int rowN;
    private int[][] moveAround = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        // 0 means blocked, default value is 0
        grids = new int[N][N];

        rowN = N;
        // the hyper point 0 will connect all top row
        // the hyper point 1 will connect to all bottom row
        // Thus we have two more
        uf = new QuickFindUF(N * N + 2);

//        for (i=2; i<N+2; i++) {
//            uf.union(i, 0);
//        }
    }

    public void unionAdjacent(int row, int col) {
        for (int[] move: moveAround) {
            int rowAdj = row + move[0];
            int colAdj = col + move[1];
            if ((rowAdj >= 0) && (rowAdj < rowN) && (colAdj >= 0) && (colAdj < rowN) && (grids[rowAdj][colAdj] == 1)) {
                uf.union(gridToDSIndex(row, col), gridToDSIndex(rowAdj, colAdj));
            }
        }
    }

    public int gridToDSIndex(int row, int col) {
        return row * rowN + col + 2;
    }

    public void connect(int row, int col) {
        if (row == 0) {
            // connect top row to hyper point 0
            uf.union(col + 2, 0);
        } else if (row == rowN - 1) {
            // connect any bottom row to hyper point 1
            uf.union(gridToDSIndex(row, col), 1);
        }
        // point in the middle, join with adjacent points
        unionAdjacent(row, col);

    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        // 1 means open
        grids[row][col] = 1;
        connect(row, col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return grids[row][col] == 1;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col)  {
        return isOpen(row, col) && uf.connected(gridToDSIndex(row, col), 0);
        }

    // number of open sites
    public int numberOfOpenSites() {
        int openN = 0;
        for (int[] arr: grids) {
            openN += Arrays.stream(arr).sum();
        }
        return openN;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(0, 1);
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {
    }
}

