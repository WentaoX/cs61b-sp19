import static org.junit.Assert.*;
import org.junit.Test;

public class TestExperimentHelper {
    ExperimentHelper eh = new ExperimentHelper();

    @Test
    public void testOptimalIPL() {
        assertEquals (0, eh.optimalIPL(1));
        assertEquals (1, eh.optimalIPL(2));
        assertEquals (2, eh.optimalIPL(3));
        assertEquals (4, eh.optimalIPL(4));
        assertEquals (6, eh.optimalIPL(5));
        assertEquals (8, eh.optimalIPL(6));
        assertEquals (10, eh.optimalIPL(7));
        assertEquals (13, eh.optimalIPL(8));
    }

    @Test
    public void testOptimalAverageDepth () {
        assertEquals(0, eh.optimalAverageDepth(1), 0.001);
        assertEquals(1.2, eh.optimalAverageDepth(5), 0.001);
        assertEquals(1.625, eh.optimalAverageDepth(8), 0.001);
    }
}
