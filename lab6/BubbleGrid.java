/* The idea is this:
1. convert the bubble grid to disjoint set, with (n_row*n_column + 1) element. The extra one element is 0, which server
as root of bubbles that will not fall.
2.  This connected problem can be solved with disjoint set, disjoint set change from the direction of not connected to
connected, however, this problem is reversed (from connected to disconnected), thus we want to solve this problem
reversly. We check all darts, when:
a 1 (bubble) is hit, we set it to 2,
a 0(empty) is hit, no need to change,
a top bubble hit, no change;
we have distinguished empty bubble and hit bubble.
3.  recursively join each possible bubble to bubble 0, calculate the size of root 0, this is the size of left bricks
after last dart.
4. We add back the hit bubble reversly, for each iteration of darts:
if bubble grid is 1, no change
if bubble grid is 0, no change, this is empty
if bubble grid is 2, this is hit bubble, set it back to 1, and go back to 3.
 */

import static java.lang.Integer.max;

public class BubbleGrid {
    private int[][] bubbleGrid;
    private int[] fallBubbleCount;
    private UnionFind ds;
    private int[][] moveDelta = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public BubbleGrid(int[][] bubbleGrid) {
        this.bubbleGrid = bubbleGrid;
    }

    int[] popBubbles(int[][] darts) {
        fallBubbleCount = new int[darts.length];
        /* with (n_row*n_column + 1) element. The extra one element is 0, which server
        as root of bubbles that will not fall. */
        ds = new UnionFind(bubbleGrid.length * bubbleGrid[0].length + 1);
        /* connect top row to 0*/
        for (int columnN = 0; columnN < bubbleGrid[0].length; columnN++) {
            if (bubbleGrid[0][columnN] == 1) {
                ds.union(columnN + 1, 0);
            }
        }

        /* change bubbleGrid status */
        throwAllDarts(darts);

        /* connect all others */
        for (int r = 1; r < bubbleGrid.length; r++) {
            for (int c = 0; c < bubbleGrid[0].length; c++) {
                unionNeighbor(r, c);
            }
        }

        int dartsN = darts.length;
        for (int i = dartsN - 1; i >= 0; i--) {
            int previousBubbleN = getExistingBubbleCount();
            fillBackBubble(darts[i]);
            unionNeighbor(darts[i][0], darts[i][1]);
            int currentBubbleN = getExistingBubbleCount();
            int falledBubbleN = max(currentBubbleN - previousBubbleN - 1, 0);
            fallBubbleCount[i] = falledBubbleN;
        }
        return fallBubbleCount;
    }

    public int getExistingBubbleCount() {
        return ds.sizeOf(0);
    }

    public void unionNeighbor(int r, int c) {
        if (bubbleGrid[r][c] == 1) {
            int currentNode = arryLocToDsLoc(r, c, bubbleGrid[0].length);
            for (int[] delta : moveDelta) {
                int rN = r + delta[0];
                int cN = c + delta[1];
                if (rN >= 0 && rN < bubbleGrid.length && cN >= 0 && cN < bubbleGrid[0].length && bubbleGrid[rN][cN] == 1) {
                    ds.union(arryLocToDsLoc(rN, cN, bubbleGrid[0].length), currentNode);
                }
            }
        }
    }

    public int arryLocToDsLoc(int i, int j, int colN) {
        return (i * colN + j + 1);
    }

    public void throwAllDarts(int[][] darts) {
        for (int[] dart : darts) {
            if (dart[0] > 0 && bubbleGrid[dart[0]][dart[1]] == 1) {
                bubbleGrid[dart[0]][dart[1]] = 2;
            }
        }
    }

    public void fillBackBubble(int[] dart) {
        if (dart[0] > 0 && bubbleGrid[dart[0]][dart[1]] == 2) {
            bubbleGrid[dart[0]][dart[1]] = 1;
        }
    }

    public int[][] peekGrid() {
        return bubbleGrid;
    }
}


