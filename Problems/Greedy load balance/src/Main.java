import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        int loadOfQueue1 = 0;
        int loadOfQueue2 = 0;
        Queue<Integer> q1 = new ArrayDeque<>();
        Queue<Integer> q2 = new ArrayDeque<>();

        var scanner = new Scanner(System.in);
        int tasks = scanner.nextInt();

        for (int i = 0; i < tasks; i++) {
            int taskId = scanner.nextInt();
            int taskLoad = scanner.nextInt();
            if (loadOfQueue1 <= loadOfQueue2) {
                loadOfQueue1 += taskLoad;
                q1.offer(taskId);
            } else {
                loadOfQueue2 += taskLoad;
                q2.offer(taskId);
            }
        }
        q1.forEach(e -> System.out.printf("%d ", e));
        System.out.println();
        q2.forEach(e -> System.out.printf("%d ", e));
    }
}