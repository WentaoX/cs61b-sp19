public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> charDeque = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            charDeque.addLast(c);
        }
        return charDeque;
    }
}
