package hw2;
import org.junit.Test;
import edu.princeton.cs.algs4.Stopwatch;

import static org.junit.Assert.*;


public class TestPercolationStats {

    @Test
    public void testMean() {
        Stopwatch sw = new Stopwatch();
        PercolationStats ps = new PercolationStats(50, 1000, new PercolationFactory());
        double time = sw.elapsedTime();

        System.out.println(ps.mean());
        System.out.println(ps.stddev());
        System.out.println(ps.confidenceHigh());
        System.out.println(ps.confidenceLow());
        System.out.println("Elapese time is " + time + 's');
    }
}
