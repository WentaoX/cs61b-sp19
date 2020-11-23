package bearmaps;
import edu.princeton.cs.algs4.SparseVector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{
    private ArrayList<Cell> minPQ;
    private HashMap<T, Double> itemMap;
    int heapSize;

    public ArrayHeapMinPQ() {
        minPQ = new ArrayList<Cell>();
        //Makes computation of children/parents “nicer”
        minPQ.add(new Cell(null, 0.0));
        itemMap = new HashMap<T, Double>();
        heapSize = 0;
    }


    private class Cell<T> {
        private T item;
        private double priority;

        public Cell(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) throw new IllegalArgumentException();
        minPQ.add(new Cell(item, priority));
        heapSize++;
        swapUntilOk(getSize());
        itemMap.put(item, priority);
    }

    @Override
    public boolean contains(T item) {
        if (itemMap.containsKey(item)) return true;
        return false;
    }

    /** Check if current element should be swapped with parent or not **/
    public void swapUntilOk(int i) {
        if (i==1) return;
        int parentIndex = getParentIndex(i);
        if (minPQ.get(parentIndex).priority <= minPQ.get(i).priority) return;
        Cell temp = minPQ.get(parentIndex);
        minPQ.set(parentIndex, minPQ.get(i));
        minPQ.set(i, temp);
        swapUntilOk(parentIndex);
    }

    public int getParentIndex(int i) {
        /** leftChild(k) = k*2
         rightChild(k) = k*2 + 1
         parent(k) = k/2 **/
        return i/2;
    }


    @Override
    public T getSmallest() {
        if (getSize() == 0) throw new NoSuchElementException();
        return (T) minPQ.get(1).item;
    }

    public int getSize() {
        return heapSize;
    }

    @Override
    public T removeSmallest() {
        if (getSize() == 0) throw new NoSuchElementException();
        Cell temp = minPQ.get(1);
        int lastIndex = getSize();
        minPQ.set(1, minPQ.get(lastIndex));
        minPQ.remove(lastIndex);
        heapSize--;

        sinkUntilOk(1);
        itemMap.remove(temp.item);
        return (T) temp.item;
    }

    /** parent maybe bigger than child, swap with child until it is smaller than child **/
    private void sinkUntilOk(int i) {
        int lastIndex = getSize();
        if (i==lastIndex) return;
        int leftChildIndex = i*2;
        int rightChildIndex = i*2 + 1;
        if (lastIndex < leftChildIndex) return;
        // only have left child
        if (lastIndex == leftChildIndex) {
            if (minPQ.get(i).priority > minPQ.get(leftChildIndex).priority) {
                swap(i, leftChildIndex);
                return;
            }
        }

        // find which child is smaller, than swap index i with that child
        int smallerChildIndex;
        if (minPQ.get(leftChildIndex).priority > minPQ.get(rightChildIndex).priority) {
            smallerChildIndex = rightChildIndex;
        } else {
            smallerChildIndex = leftChildIndex;
        }

        if (minPQ.get(smallerChildIndex).priority < minPQ.get(i).priority) {
            swap(i, smallerChildIndex);
            sinkUntilOk(smallerChildIndex);
        }
    }

    private void swap(int a, int b) {
        Cell temp = minPQ.get(a);
        minPQ.set(a, minPQ.get(b));
        minPQ.set(b, temp);
    }

    @Override
    public int size() {
        return getSize();
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!itemMap.containsKey(item)) throw new NoSuchElementException();
        double currentPriority = itemMap.get(item);
        int index = findIndex(item, currentPriority);
        minPQ.get(index).priority = priority;
        if (index==1) {
            sinkUntilOk(1);
        } else if (index == (getSize())) {
            swapUntilOk(index);
        } else {
            int parentIndex = index/2;
            if (minPQ.get(index).priority < minPQ.get(parentIndex).priority) {
                swapUntilOk(index);
            } else {
                sinkUntilOk(index);
            }
        }

    }

    public int findIndex(T item, double priority) {
        int index = findIndex(item, priority, 1);
        return index;
    }

    private int findIndex(T item, double priority, int index) {
        if (index > getSize()) return -1;
        Cell cell = minPQ.get(index);
        if (cell.item.equals(item)) {
            return index;
        }
        // priority already higher than current one, no need to go further
        if (cell.priority > priority) return -1;

        int leftReturn = findIndex(item, priority, index/2);
        if (leftReturn != -1) return leftReturn;
        int rightReturn = findIndex(item, priority, index/2 + 1);
        if (rightReturn != -1) return rightReturn;
        return -1;
        }
}
