public class UnionFind {

//    Use values other than -1 in parent array for root nodes to track size.
    private int[] parentArray;
//    private Map<Integer, Integer> treeHeight = new HashMap<>();

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parentArray = new int[n];
        for (int i = 0; i < n; i++ ) {
            parentArray[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if ((vertex < 0) || (vertex >= parentArray.length)) {
            throw new IllegalArgumentException("" + vertex + " not in set");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        int parent = parentArray[v1];
        while (parent >= 0) {
            parent = parentArray[parent];
        }
        return -parent;
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        return parentArray[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        if (find(v1) == find(v2)) {
            return true;
        }
        return false;
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        int root1 = find(v1);
        int root2 = find(v2);
        if (root1 != root2) {
            int root1_size = -parentArray[root1];
            int root2_size = -parentArray[root2];
            if (root1_size > root2_size) {
                // root1 is bigger, connect root2 to root1
                parentArray[root2] = root1;
                // update root1 size
                parentArray[root1] -= root2_size;
            } else {
                parentArray[root1] = root2;
                parentArray[root2] -= root1_size;
            }
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        int parent = vertex;
        while(parentArray[parent] >= 0) {
            parent = parentArray[parent];
        }

        // path compression
        int parent_c = vertex;
        int parent_parent;
        while(parent_c != parent) {
            parent_parent = parentArray[parent_c];
            parentArray[parent_c] = parent;
            parent_c = parent_parent;
        }

        return parent;
    }

}
