import static org.junit.Assert.*;
import org.junit.Test;


public class FlikTest {

    @Test
    public void testisSameNumber() {
        int i = 500;
        int j = 500;
        int k = 5;
        int q = 5;
        assertEquals(false, Flik.isSameNumber(i, k));
        assertTrue(Flik.isSameNumber(k, q));
        assertTrue(Flik.isSameNumber(i, j));
    }
}
