import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Deque<Integer> workingStack = new ArrayDeque<>();
        Deque<Integer> maxStack = new ArrayDeque<>();

        var scanner = new Scanner(System.in);
        int commandCount = scanner.nextInt();
        for (int i = 0; i < commandCount; i++) {
            String command = scanner.next();
            int data = 0;
            if ("push".equals(command)) {
                data = Integer.parseInt(scanner.next());
                workingStack.offerFirst(data);
                if (maxStack.isEmpty()) {
                    maxStack.offerFirst(data);
                } else {
                    Integer max = Integer.max(data, maxStack.peekFirst());
                    maxStack.offerFirst(max);
                }
            }
            if ("pop".equals(command)) {
                workingStack.pollFirst();
                maxStack.pollFirst();
            }
            if ("max".equals(command)) {
                System.out.println(maxStack.peekFirst());
            }
        }
    }
}