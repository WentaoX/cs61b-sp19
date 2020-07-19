

public class LinkedListDeque<T> {
    public class ItemNode {
        public T item;
        public ItemNode next;
        public ItemNode previous;
        public ItemNode(T c, ItemNode N, ItemNode P) {
            item = c;
            next = N;
            previous = P;
        }
    }
    private ItemNode sentinel; /* always the first element*/
    private int size;

    public LinkedListDeque() {
        sentinel = new ItemNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.previous = sentinel;
        size = 0;
    }

    public LinkedListDeque(T x) {
        sentinel = new ItemNode(null, null, null);
        ItemNode first = new ItemNode(x, sentinel, sentinel);
        sentinel.previous = first;
        size = 1;
    }

    /** Adds an item to the front of the list. */
    public void addFirst(T item) {
        ItemNode first = new ItemNode(item, sentinel.next, sentinel);
        size += 1;
    }

    /** Retrieves the front item from the list. */
    public T getFirst() {
        return sentinel.next.item;
    }

    public boolean isEmpty() {
        boolean b = size == 0;
        return b;
    }

    /** Adds an item to the end of the list. */
    public void addLast(T item) {

        ItemNode last = new ItemNode(item, sentinel, sentinel.previous);
        size += 1;
    }

    public void printDeque() {
        ItemNode tmp = sentinel.next;
        while(tmp.next != sentinel) {
            System.out.print(tmp.item);
            System.out.println(" ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            ItemNode tmp = sentinel.next;
            sentinel.next = sentinel.next.next;
            sentinel.next.previous = sentinel;
            this.size -= 1;
            T it = tmp.item;
            tmp = null;
            return it;
        }
    }

    public T removeLast() {
        if (size==0) {
            return null;
        } else {
            ItemNode tmp = sentinel.previous;
            sentinel.previous = sentinel.previous.previous;
            sentinel.previous.next = sentinel;
            size -= 1;
            T it = tmp.item;
            tmp = null;
            return it;
        }
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        } else {
            ItemNode tmp = sentinel.next;
            while(index > 0) {
                tmp = tmp.next;
            }
            return tmp.item;
        }
    }

    /** Returns the number of items, must be constant. */
    public int size() {
        return size;
    }
}