import java.util.ArrayList;
import java.util.List;

public class MyTrieSet implements TrieSet61B{

    private static final int R = 256;        // extended ASCII

    private Node root;      // root of trie
    private int n;          // number of keys in trie

    // R-way trie node
    private static class Node {
        private boolean isKey;
        private Node[] next = new Node[R];
    }

    /**
     * Initializes an empty string symbol table.
     */
    public MyTrieSet() {
        n=0;
    }

    /** Clears all items out of Trie */
    @Override
    public void clear() {
        for (int c = 0; c < R; c++) {
            root.next[c] = null;
        }
        n=0;
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    @Override
    public boolean contains(String key) {
        return contains(root, key, 0);
    }

    private boolean contains(Node x, String key, int d) {
        if (x == null) return false;
        if (d == key.length()) {
            return x.isKey;
        }

        //
        char c = key.charAt(d);
        return contains(x.next[c], key, d+1);
    }

    /** Inserts string KEY into Trie */
    @Override
    public void add(String key) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        else root = add(root, key, 0);
    }

    private Node add(Node x, String key, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            // if isKey == true, the key already exists
            if (x.isKey == false) {
                n++;
                x.isKey = true;
            }
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = add(x.next[c], key, d+1);
        return x;
    }

    /** Returns a list of all words that start with PREFIX */
    /**
     * Returns all of the keys in the set that start with {@code prefix}.
     * @param prefix the prefix
     * @return all of the keys in the set that start with {@code prefix},
     *     as an iterable
     */
    @Override
    public List<String> keysWithPrefix(String prefix) {
        ArrayList<String> results = new ArrayList<String>();
        Node x = get(root, prefix, 0);
        collect(x, new StringBuilder(prefix), results);
        return results;
    }

    private void collect(Node x, StringBuilder prefix, ArrayList<String> results) {
        if (x == null) return;
        if (x.isKey == true) results.add(prefix.toString());
        for (char c = 0; c < R; c++) {
            //choose
            prefix.append(c);
            //explore
            collect(x.next[c], prefix, results);
            // unchoose
            prefix.deleteCharAt(prefix.length() - 1);
        }
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c], key, d+1);
    }

    /**
     * Returns the string in the symbol table that is the longest prefix of {@code query},
     * or {@code null}, if no such string.
     * @param query the query string
     * @return the string in the symbol table that is the longest prefix of {@code query},
     *     or {@code null} if no such string
     * @throws IllegalArgumentException if {@code query} is {@code null}
     */
    public String longestPrefixOf(String query) {
        if (query == null) throw new IllegalArgumentException("argument to longestPrefixOf() is null");
        int length = longestPrefixOf(root, query, 0, -1);
        if (length == -1) return null;
        else return query.substring(0, length);
    }

    // returns the length of the longest string key in the subtrie
    // rooted at x that is a prefix of the query string,
    // assuming the first d character match and we have already
    // found a prefix match of given length (-1 if no such match)
    private int longestPrefixOf(Node x, String query, int d, int length) {
        if (x == null) return length;
        if (x.isKey == true) length = d;
        if (d == query.length()) return length;
        char c = query.charAt(d);
        return longestPrefixOf(x.next[c], query, d+1, length);
    }

}
