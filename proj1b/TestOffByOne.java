import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testEqualChars() {
        boolean test1 = offByOne.equalChars('a', 'b');
        boolean test2 = offByOne.equalChars('a', 'c');
        boolean test3 = offByOne.equalChars('r', 'q');

        assertTrue(test1);
        assertFalse(test2);
        assertTrue(test3);
    }
} // Uncomment this class once you've created your CharacterComparator interface and OffByOne class. **/