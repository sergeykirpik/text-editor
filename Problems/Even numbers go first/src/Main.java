import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        var scanner = new Scanner(System.in);
        Deque<Integer> numbers = new ArrayDeque<>();
        int count = scanner.nextInt();
        for (int i = 0; i < count; i++) {
            int num = scanner.nextInt();
            if (num % 2 == 0) {
                numbers.offerFirst(num);
            } else {
                numbers.offerLast(num);
            }
        }
        while (!numbers.isEmpty()) {
            System.out.println(numbers.poll());
        }
    }
}