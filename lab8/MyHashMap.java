import edu.princeton.cs.algs4.Queue;

import java.util.HashSet;
import java.util.Iterator;
//import java.util.Queue;
import java.util.Set;


public class MyHashMap<K, V> implements Map61B<K,V> {
    private int initialSize=16;
    private Set<K> keySet;
    private SequentialSearchST<K, V>[] st;
    private int m;                                  // the number of hash map pairs
    private double loadFactor;                      // loadFactor to rescale, this is defined as (pairSize/m)
    private int pairSize=0;                         // number of key-value pairs


    private class SequentialSearchST<K, V> {
        private Node firstNode;
        private int n=0; // the length of the sequential

        private class Node<K, V> {
            private K key;
            private V val;
            private Node next;

            public Node(K key, V val, Node next) {
                this.key = key;
                this.val = val;
                this.next = next;
            }
        }

        public SequentialSearchST() {};

        public void put (K key, V val) {

            for (Node node = firstNode; node != null; node = node.next) {
                if (node.key.equals(key)) {
//                  already have the key, update the value
                    node.val = val;
                    return;
                }
            }
            //                There is no such key, add to the first
            firstNode = new Node(key, val, firstNode);
            n++;
        }

        public boolean containsKey(K key) {
            for (Node node = firstNode; node != null; node = node.next) {
                if (node.key.equals(key)) {
//                  already have the key, update the value
                    return true;
                }
            }
            return false;
        }

//        Removes the mapping for the specified key from this map if present.
        public V remove (K key) {
            if (firstNode == null) {
                return null;
            }
            if (firstNode.key.equals(key)) {
                V val = (V) firstNode.val;
                firstNode = firstNode.next;
                n--;
                return val;
            }
            for (Node node = firstNode; node.next != null; node = node.next) {
                if (node.next.key.equals(key)) {
                    V val;
                    val = (V) node.next.val;
                    node.next = node.next.next;
                    n--;
                    return val;
                }
            }
            return null;
        }

        //        Removes the mapping for the specified key from this map if present.
        public V remove (K key, V val) {
            if (firstNode == null) {
                return null;
            }
            if (firstNode.key.equals(key) && firstNode.val.equals(val)) {
                firstNode = firstNode.next;
                n--;
                return val;

            }
            for (Node node = firstNode; node.next != null; node = node.next) {
                if (node.next.key.equals(key) && node.next.val.equals(val)) {
                    node.next = node.next.next;
                    n--;
                    return val;
                }
            }
            return null;
        }

        public V get(K key) {
            return get(firstNode, key);
        }

        private V get(Node node, K key) {
            if (node == null) return null;
            if (node.key.equals(key)) return (V) node.val;
            return (V) get(node.next, key);
            }

        public void clear() {
            if (firstNode != null) {
                firstNode = clear(firstNode);
                n = 0;
            }
        }

        private Node clear(Node node) {
            if (node.next == null) {
                return null;
            }
            node.next = clear(node.next);
            return node;
        }

        public int getSize() {return n;}

        /**
         * Returns all keys in the symbol table as an {@code Iterable}.
         * To iterate over all of the keys in the symbol table named {@code st},
         * use the foreach notation: {@code for (Key key : st.keys())}.
         * @return all keys in the symbol table as an {@code Iterable}
         */
        public Iterable<K> keys()  {
            Queue<K> queue = new Queue<K>();
            for (Node x = firstNode; x != null; x = x.next)
                queue.enqueue((K) x.key);
            return queue;
        }

//        /** An iterator that iterates over the keys of the dictionary. */
//        private class SearchST implements Iterator<K> {
//
//            /** Create a new ULLMapIter by setting cur to the first node in the
//             *  linked list that stores the key-value pairs. */
//            public SearchST() {
//                cur = firstNode;
//            }
//
//            @Override
//            public boolean hasNext() {
//                return cur != null;
//            }
//
//            @Override
//            public K next() {
//                K ret = (K) cur.key;
//                cur = cur.next;
//                return ret;
//            }
//
//
//            /** Stores the current key-value pair. */
//            private Node cur;
//
//        }
    }


    public MyHashMap() {
        this.m = 16;
        this.loadFactor = 0.75;
        initializeST(m);
        keySet = new HashSet();
    }

    public MyHashMap(int initialSize) {
        this.m = initialSize;
        this.loadFactor = 0.75;
        initializeST(m);
        keySet = new HashSet();
    }

// initialize
    public MyHashMap(int initialSize, double loadFactor) {
        // initialize
        this.m = initialSize;
        this.loadFactor = loadFactor;
        initializeST(m);
        keySet = new HashSet();
    }

    private void initializeST(int m) {
        st = (SequentialSearchST<K, V>[]) new SequentialSearchST[m];
        for (int i = 0; i < m; i++)
            st[i] = new SequentialSearchST<K, V>();
    }

    /** Removes all of the mappings from this map. */
    public void clear() {
        for(SequentialSearchST s: st) s.clear();
        initializeST(initialSize);
        keySet = new HashSet<>();
        this.m = initialSize;
        pairSize=0;
    }

    // hash value between 0 and m-1
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        int i = hash(key);
        return st[i].containsKey(key);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        int i = hash(key);
        return st[i].get(key);
    }

    /** Returns the number of key-value mappings in this map. */
    public int size() {return pairSize;}

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("argument to put() is null");
        }

        int i = hash(key);
        if (!st[i].containsKey(key)) {
            pairSize++;
            keySet.add(key);
        }
        st[i].put(key, value);
        if ((double) pairSize/m > loadFactor) {
            rescale();
        }
    }

    private void rescale() {
        int oldM = this.m;
        // keep record of the old Sequential array
        SequentialSearchST<K, V>[] oldST = new SequentialSearchST[oldM];
        for (int i = 0; i<oldM; i++) {
            SequentialSearchST<K, V> stTemp = new SequentialSearchST<K, V>();
            for (K key: st[i].keys()) {
                stTemp.put(key, st[i].get(key));
            }
            oldST[i] = stTemp;
        }
        this.m = this.m * 2;
        initializeST(this.m);
        pairSize=0;
        for (SequentialSearchST<K, V> s: oldST) {
            for (K key: s.keys()) {
                V val = s.get(key);
                this.put(key, val);
            }
        }
    }

    /** Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        return keySet;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    public V remove(K key) {
        if (key == null) throw new IllegalArgumentException("argument to put() is null");
        int i = hash(key);
        if (st[i].containsKey(key)) {
            pairSize--;
            keySet.remove(key);
            return st[i].remove(key);
        }
        return null;
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    public V remove(K key, V value) {
        if (key == null) throw new IllegalArgumentException("argument to put() is null");
        int i = hash(key);
        if (st[i].containsKey(key)) {
            int nBeforeRemove = st[i].getSize();
            V val = st[i].remove(key, value);
            // not sure if map contains (key, value), judge from getSize()
            if (st[i].getSize() != nBeforeRemove) {
                pairSize--;
                keySet.remove(key);
            };
            return val;
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet.iterator();
    }
}
