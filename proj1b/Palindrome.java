public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> charDeque = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            charDeque.addLast(c);
        }
        return charDeque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> dd = wordToDeque(word);
        if (dd.size() <= 1) {
            return true;
        } else {
            while (dd.size() > 1) {
                Character first = dd.removeFirst();
                Character last =  dd.removeLast();
                if (first != last) {
                    return false;
                }
            }
            return true;
        }
    }
}
