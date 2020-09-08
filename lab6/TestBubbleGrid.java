import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class TestBubbleGrid {

    private int[][] bubbles = {
            {1, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 0},
            {1, 0, 0, 1, 0, 0},
            {1, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 0},
    };

    private int[][] darts = {
            {1,0}, {2, 2}, {1, 3}
    };

    @Test
    public void testthrowAllDarts() {
        BubbleGrid bubbleGrid = new BubbleGrid(bubbles);
        bubbleGrid.throwAllDarts(darts);
        int [][] expectedBubbles = {
                {1, 0, 0, 0, 0, 0},
                {2, 1, 1, 2, 0, 0},
                {1, 0, 0, 1, 0, 0},
                {1, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 0},
        };
        assertArrayEquals(expectedBubbles, bubbleGrid.peekGrid());
    }

    @Test
    public void testPopBubbles() {
        int[][] bubbles2 = new int[][]{
                {1, 1, 0},
                {1, 0, 0},
                {1, 1, 0},
                {1, 1, 1}};
        int[][] darts2 = new int[][]{{2, 2}, {2, 0}};
        BubbleGrid bubbleGrid = new BubbleGrid(bubbles2);
        int[] expectedCount = new int[]{0, 4};
        int[] returned =  bubbleGrid.popBubbles(darts2);
        System.out.println(Arrays.toString(returned));
        assertArrayEquals(expectedCount, returned);

    }

}
