import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Deque<Integer> stack = new ArrayDeque<>();
        var scanner = new Scanner(System.in);
        int count = scanner.nextInt();
        for (int i = 0; i < count; i++) {
            int num = scanner.nextInt();
            stack.offerFirst(num);
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.poll());
        }
    }
}