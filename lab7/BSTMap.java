import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;

    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private int size;

        public Node(K key, V value, int size) {
            this.key = key;
            this.value = value;
            this.size = size;  // number of nodes in subtree
        }
    }

    /** Removes all of the mappings from this map. */
    public void clear() {
        clear(root);
        root = null;
    }

    private Node clear(Node node) {
        if (node == null) return null;
        node.left = clear(node.left);
        node.right = clear(node.right);
        node = null;
        return node;
    }


    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        return haveKey(root, key);
    }

    private boolean haveKey(Node node, K key) {
        if (node == null) return false;
        if (node.key.equals(key)) return true;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            // key is smaller than node.key, should search node.left
            if (node.left == null) return false;
            else return haveKey(node.left, key);
        } else {
            if (node.right == null) return false;
            else return haveKey(node.right, key);
        }
    }

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node node, K key) {
        if (node == null) return null;
        if (node.key.equals(key)) return node.value;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            // key is smaller than node.key, should search node.left
            if (node.left == null) return null;
            else return get(node.left, key);
        } else {
            if (node.right == null) return null;
            else return get(node.right, key);
        }
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        if (root == null) return 0;
        return size(root);
    }

    private int size(Node node) {
        if (node == null) return 0;
        else return node.size;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        root = put(root, key, value);
    }

    // important
    private Node put(Node x, K key, V val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left,  key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else              x.value   = val;
        x.size = 1 + size(x.left) + size(x.right);
        return x;
        }

    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

}
