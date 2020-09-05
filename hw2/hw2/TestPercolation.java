package hw2;

import org.junit.Test;
import static org.junit.Assert.*;


public class TestPercolation {

    @Test
    public void testOpen() {
        Percolation pcl = new Percolation(4);
        assertFalse(pcl.isOpen(1, 0));

        pcl.open(1, 0);
        assertTrue(pcl.isOpen(1, 0));
        assertFalse(pcl.isFull(1, 0));

        pcl.open(0, 1);
        assertTrue(pcl.isOpen(1, 0));
        assertFalse(pcl.isFull(1, 0));

        pcl.open(0, 0);
        assertTrue(pcl.isFull(0, 0));
        assertTrue(pcl.isFull(1, 0));
        assertEquals(3, pcl.numberOfOpenSites());
        assertFalse(pcl.percolates());

        pcl.open(2,0);
        pcl.open(2, 1);
        assertFalse(pcl.percolates());

        pcl.open(3, 0);
        assertTrue(pcl.percolates());
    }
}
