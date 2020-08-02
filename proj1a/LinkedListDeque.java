

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
        sentinel.previous = sentinel;
        sentinel.next= sentinel;
        size = 0;
    }

    public LinkedListDeque(T x) {
        sentinel = new ItemNode(null, null, null);
        sentinel.next = new ItemNode(x, sentinel, sentinel);
        size = 1;
    }

    /** Adds an item to the front of the list. */
    public void addFirst(T item) {
        ItemNode itemNode = new ItemNode(item, sentinel.next, sentinel);
        sentinel.next = itemNode;
        sentinel.next.next.previous = itemNode;
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
        sentinel.previous = last;
        sentinel.previous.previous.next = last;
        size += 1;
    }

    public void printDeque() {
        ItemNode tmp = sentinel.next;
        while(tmp != sentinel) {
            System.out.print(tmp.item);
            System.out.println(" ");
            tmp = tmp.next;
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
            tmp.item = null;
            tmp.previous = null;
            tmp.next = null;
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
            tmp.item = null;
            tmp.previous = null;
            tmp.next = null;
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