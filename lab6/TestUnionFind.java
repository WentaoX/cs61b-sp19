import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;


public class TestUnionFind {

    @Test
    public void testConnected() {
        UnionFind union = new UnionFind(5);

        assertFalse(union.connected(0, 3));

        assertEquals(3, union.find(3));
         union.union(0, 3);
        assertEquals(3, union.parent(0));
        assertTrue(union.connected(0, 3));

        assertFalse(union.connected(0, 1));
        union.union(1, 3);
        assertTrue(union.connected(0, 3));

        assertEquals(3, union.sizeOf(0));
        assertEquals(1, union.sizeOf(4));
        assertEquals(1, union.sizeOf(2));

        union.union(1, 3);
        assertEquals(3, union.sizeOf(0));
        assertEquals(1, union.sizeOf(4));
        assertEquals(1, union.sizeOf(2));
        assertTrue(union.connected(1, 3));
    }
}
