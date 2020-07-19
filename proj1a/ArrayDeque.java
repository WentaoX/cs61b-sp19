public class ArrayDeque<T> {
    private int size;
    private int nextFirst;
    private int nextLast;
    private int length;
    private T[] items;

    public ArrayDeque() {
        size = 0;
        nextFirst = 3;
        nextLast = 4;
        length = 8;
        items = (T[]) new Object[length];
    }

    public ArrayDeque(ArrayDeque<T> other) {
        size = other.size;
        nextFirst = other.nextFirst;
        nextLast = other.nextLast;
        length  = other.length;
        items = (T[]) new Object[length];
        System.arraycopy(other, 0, items, 0, length);
    }

    /* move firstForward, if first already 0, should move to the last, this is circular*/
    private void moveFirstForward() {
        if (nextFirst > 0) {
            nextFirst--;
        } else {
            nextFirst = length - 1; /* index start from 0*/
        }
    }

    /* if item already full, resize and double the length.
    if the array is full, two cases for relationship of nextLast and nextFirst
    Case 1. nextLast is 1 larger than nextFirst, we should insert empty array between last(nextLast-1) and first(nextFirst+1)
        3 steps:
        1. create a new array that double the original size,
        2. copy old[0 to last] to new[0 to last], new nextLast = old nextLast
        3. copy old[first to length-1) to new[first + length to 2*length-1], new nextFirst = old nextFirst + length
    Case 2. nextLast=0, nextFirst=length-1
        this means last = length-1, first = 0
        2 steps:
        1. create a new array that double the original size,
        2. copy old[0 to length] to new[0 to length - 1], new nextLast = length, new nextFirst = 2*length-1
     */
    private void resizeIncrease() {
        if (size == length) {
            int newLength = length * 2;
            int lengthDelta = newLength - length;
            T[] newItems = (T[]) new Object[newLength];
            /* case 1 */
            if (nextLast - nextFirst == 1) {
                System.arraycopy(items, 0, newItems, 0, nextLast);
                /* copied length = (length - 1) - (nextFirst + 1) + 1 = length-nextFirst-1 */
                System.arraycopy(items, nextFirst + 1, newItems, nextFirst + 1 + lengthDelta,
                        length - nextFirst - 1);
                nextFirst = nextFirst + lengthDelta;
            } else {
                /* case 2 */
                System.arraycopy(items, 0, newItems, 0, length);
                nextLast = length;
                nextFirst = newLength - 1;
            }
            length = newLength;
            items = newItems;
        }
    }

    /* add 1, if nextLast == length - 1, change to 0*/
    private void moveLastForward() {
        if (nextLast == length - 1) {
            nextLast = 0;
        } else {
            nextLast ++;
        }
    }

    public void addFirst(T item) {
        resizeIncrease();
        items[nextFirst] = item;
        size += 1;
        moveFirstForward();
    }

    public void addLast(T item) {
        resizeIncrease();
        items[nextLast] = item;
        size += 1;
        moveLastForward();
    }

    public boolean isEmpty() {
        return size==0;
    }

    public int size() {
        return size;
    }

    /* print from nextFirst+1 until nextLast-1 */
    public void printDeque() {
        if (size != 0) {
            int index;
            if (nextFirst == length-1) {
                index = 0;
            } else {
                index = nextFirst + 1;
            }
            while (index != nextLast) {
                System.out.print(items[index]);
                System.out.print(' ');
                /* index move to next 1 */
                if (index == length - 1) {
                    index = 0;
                } else {
                    index++;
                }
            }
            System.out.println();
        }
    }

    /* if length > 16, and usage < 25%, start to resize, resize to half
    Two case:
    case 1, nextFirst < nextLast, datasets are in the middle, two setps:
        1, create new array only half size
        2, copy old[firstNext+1, nextLast-1] to new[1, size], new nextFirst=0, new nextLast=size+1]
     Case 2, nextFirst > nextLast, datasets are in front and end, middle part is empty, three steps:
        1. create new array only half size
        There are 3 sub  cases too.
            sub case 1, nextLast=0, all values in the latter part.
                copy old[firstNext+1, length-1] to new[1, size], new nextFirst=0, new nextLast=size+1]
            sub case 2, nextFirst = length - 1, all values in the front of the array
                copy old[0, lastNext-1] to new[1, size], new nextFirst=0, new nextLast=size+1]
            sub case 3, there are values in both front and end part of the array, 2 steps.
                1. copy old[0, nextLast-1] to new[1, nextLast], new nextFirst=0
                2. copy old[nextFirst + 1, length-1] to new[1+nextLast, 1+nextLast + (length-1) - (nextFirst+1)],
                new nextLast=size+1
    */
    private void resizeDecrease() {
        if ((length >= 16) && ((float) size/length <= 0.25)) {
            int newLength = length/2;
            T[] newItems = (T[]) new Object[newLength];
            // case 1
            if (nextFirst < nextLast) {
                System.arraycopy(items, nextFirst+1, newItems, 1, size);
            } else {
                // case 2
                if (nextLast==0) {
                    // sub case 1
                    System.arraycopy(items, nextFirst+1, newItems, 1, size);
                } else if (nextFirst == length-1) {
                    //sub case 2
                    System.arraycopy(items, 0, newItems, 1, size);
                } else {
                    // sub case 3
                    System.arraycopy(items, 0, newItems, 1, nextLast);
                    System.arraycopy(items, nextFirst + 1, newItems, nextLast+1, size-nextLast );
                }
            }
            nextFirst = 0;
            nextLast = size+1;
            length = newLength;
            items = newItems;
        }

    }
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T firstItem;
            if (nextFirst == length -1) {
                firstItem = items[0];
                items[0] = null;
                nextFirst = 0;
            } else {
                firstItem = items[nextFirst + 1];
                items[nextFirst + 1] = null;
                nextFirst ++;
            }
            size --;
            resizeDecrease();
            return firstItem;
        }
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T lastItem;
            if (nextLast == 0) {
                lastItem = items[length - 1];
                items[length - 1] = null;
                nextLast = length - 1;
            } else {
                lastItem = items[nextLast - 1];
                items[nextLast - 1] = null;
                nextLast --;
            }
            size --;
            resizeDecrease();
            return lastItem;
        }
    }

    public T get(int index) {
        if (index < length) {
            return items[index];
        } else {
            return null;
        }
    }
}
