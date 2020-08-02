import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {

    @Test
    public void testEqualChars() {
        OffByN offBy5 = new OffByN(5);
        boolean test1 = offBy5.equalChars('a', 'f');  // true
        boolean test2 = offBy5.equalChars('f', 'a');  // true
        boolean test3 = offBy5.equalChars('f', 'h');  // false

        assertTrue(test1);
        assertTrue(test2);
        assertFalse(test3);
    }

}
