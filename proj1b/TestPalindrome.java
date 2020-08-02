import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        boolean test1 = palindrome.isPalindrome("abcba");
        boolean test2 = palindrome.isPalindrome("abcd");
        boolean test3 = palindrome.isPalindrome("a");
        assertTrue(test1);
        assertFalse(test2);
        assertTrue(test3);

        CharacterComparator cc = new OffByOne();
        boolean testcc1 = palindrome.isPalindrome("abcba", cc);
        boolean testcc2  = palindrome.isPalindrome("abcab", cc);
        boolean testcc3 = palindrome.isPalindrome("afgqrfeb", cc);
        assertFalse(testcc1);
        assertTrue(testcc2);
        assertTrue(testcc3);

    }

}     //Uncomment this class once you've created your Palindrome class. */