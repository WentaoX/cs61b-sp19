package bearmaps;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestExtrinsicMinPQ {

    @Test
    public void testAdd() {
        ArrayHeapMinPQ<String> minPQ = new ArrayHeapMinPQ<String>();
        minPQ.add("2", 2.0);
        minPQ.add("3", 3.0);
        minPQ.add("4", 4.0);
        minPQ.add("8", 8.0);
        minPQ.add("1", 1.0);
        minPQ.add("9", 9.0);
        assertEquals("1", minPQ.getSmallest());
        assertEquals("1", minPQ.removeSmallest());
        assertEquals("2", minPQ.removeSmallest());
    }
}
