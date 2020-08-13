package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer<Integer>(10);

        // before enqueue
        assertEquals(true, arb.isEmpty());
        assertEquals(0, arb.fillCount());

        arb.enqueue(5);
        arb.enqueue(6);

        // after enqueue
        assertEquals(5, arb.peek());
        assertEquals(10, arb.capacity());
        assertEquals(false, arb.isEmpty());
        assertEquals(false, arb.isFull());
        assertEquals(2, arb.fillCount());

        assertEquals(5, arb.dequeue());
        assertEquals(6, arb.dequeue());

        // after dequeue
        assertEquals(true, arb.isEmpty());
        assertEquals(0, arb.fillCount());
//        arb.dequeue();
    }

    @Test
    public void ringTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer<Integer>(4);

        arb.enqueue(5);
        arb.enqueue(6);
        arb.enqueue(7);

        assertEquals(5, arb.dequeue());
        assertEquals(6, arb.dequeue());
        assertEquals(7, arb.dequeue());

        arb.enqueue(8);
        arb.enqueue(9);
        arb.enqueue(10);

        assertEquals(8, arb.dequeue());
        assertEquals(9, arb.dequeue());
        assertEquals(10, arb.dequeue());

        assertEquals(true, arb.isEmpty());
        assertEquals(0, arb.fillCount());
    }
}
