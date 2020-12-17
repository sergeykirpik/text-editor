import java.util.*;

public class Main {

    public static void processIterator(String[] array) {
        List<String> list = new ArrayList<>(Arrays.asList(array));
        var iterator = list.listIterator();
        while (iterator.hasNext()) {
            String current = iterator.next();
            if (!current.startsWith("J")) {
                iterator.remove();
            } else {
                iterator.set(current.substring(1));
            }
        }
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        processIterator(scanner.nextLine().split(" "));
    }
}