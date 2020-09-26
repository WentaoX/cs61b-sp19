import java.util.Set;
import java.util.LinkedList;


public class MyHashMap<K, V> implements Map61B<K, V>{

    private Set<K> keySet;

    private class SequentialSearchST<K, V> {
        private Node firstNode;
        private int n=0; // the length of the sequential

        private class Node() {
            private K key;
            private V val;
            private Node next;

            public Node(K key, V val, Node next) {
                key = key;
                val = val;
                next = next;
            }
        }

        public SequentialSearchST() {};

        public void put (K key, V val) {

            for (Node node = firstNode; node != null; node = node.next) {
                if (node.key.equals(key)) {
//                  already have the key, update the value
                    node.val = val;
                    break;
                }

//                There is no key, add to the first
                firstNode = new Node(key, val, firstNode);
                n++;
            }
        }

        public boolean containsKey(K key) {
            for (Node node = firstNode; node != null; node = node.next) {
                if (node.key.equals(key)) {
//                  already have the key, update the value
                    return true;
                }
                return false;
            }
        }

//        Removes the mapping for the specified key from this map if present.
        public V remove (K key) {
            if (firstNode == null) {
                return null;
            }
            if (firstNode.key.equals(key)) {
                V val = firstNode.val;
                firstNode = firstNode.next;
                return val;
            }
            for (Node node = firstNode; node.next != null; node = node.next) {
                if (node.next.key.equals(key)) {
                    V val = node.next.val;
                    node.next = node.next.next;
                    return val;
                }
            }
        }

        //        Removes the mapping for the specified key from this map if present.
        public V remove (K key, V val) {
            if (firstNode == null) {
                return null;
            }
            if (firstNode.key.equals(key) && firstNode.val.equals(val)) {
                firstNode = firstNode.next;
                return val;
            }
            for (Node node = firstNode; node.next != null; node = node.next) {
                if (node.next.key.equals(key) && node.next.val.equals(val)) {
                    node.next = node.next.next;
                    return val;
                }
            }
        }

        public V get(K key) {
            return get(firstNode, key);
        }

        private V get(Node node, K key) {
            if (node == null) return null;
            if (node.key.equals(key)) return node.val;
            return get(node.next, key);
            }
        }
    }

    /** Removes all of the mappings from this map. */
    void clear();

    /** Returns true if this map contains a mapping for the specified key. */
    boolean containsKey(K key);

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    V get(K key);

    /** Returns the number of key-value mappings in this map. */
    int size();

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    void put(K key, V value);

    /** Returns a Set view of the keys contained in this map. */
    Set<K> keySet();

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    V remove(K key);

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    V remove(K key, V value);
}
