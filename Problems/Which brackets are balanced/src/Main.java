import java.util.*;

class Main {
    private static final Map<Character, Character> pairs = Map.of(
        '[', ']',
        '(', ')',
        '{', '}'
    );
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        String brackets = scanner.nextLine();
        System.out.println(isBalanced(brackets));
    }

    public static boolean isBalanced(String brackets) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < brackets.length(); i++) {
            char c = brackets.charAt(i);
            if (pairs.containsKey(c)) {
                stack.offerFirst(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character fromStack = stack.pollFirst();
                Character matchedBracket = pairs.get(fromStack);
                if (c != matchedBracket) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}