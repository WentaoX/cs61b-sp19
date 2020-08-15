package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if (fillCount < rb.length) {
            rb[last] = x;
            fillCount ++;

            // last +1
            if (last == rb.length - 1) {
                last = 0;
            } else {
                last ++;
            }

        } else {
            throw new RuntimeException("Ring buffer overflow");
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        if (fillCount > 0) {
            T temp = rb[first];
            rb[first] = null;
            fillCount --;

            // first +1
            if (first == rb.length - 1) {
                first = 0;
            } else {
                first ++;
            }
            return temp;
        } else {
            throw new RuntimeException("Ring buffer underflow");
        }
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if (fillCount > 0) {
            return rb[first];
        } else {
            throw new RuntimeException("Ring buffer underflow");
        }
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    @Override
    public boolean isEmpty() {
        if (fillCount == 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isFull() {
        if (fillCount == rb.length) {
            return true;
        } else {
            return false;
        }
    }
    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.

    private class ArrayRingIterator implements Iterator<T> {
        private int pos;
        public ArrayRingIterator () {pos = 0;}
        public boolean hasNext() {return (fillCount > (pos-1));}
        public T next() {
            T returnItem;
            if ((first + pos) < rb.length) {
                returnItem = rb[first + pos];
            } else {
                returnItem = rb[rb.length-first-pos];
            }
            pos++;
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingIterator();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        ArrayRingBuffer<T> o = (ArrayRingBuffer<T>) other;
        if (o.fillCount != this.fillCount) {
            return false;
        }
        Iterator<T> thisIterator = this.iterator();
        Iterator<T> otherIterator = o.iterator();
        while (thisIterator.hasNext() && otherIterator.hasNext()) {
            if (thisIterator.next() != otherIterator.next()) {
                return false;
            }
        }
        return true;
    }
}

