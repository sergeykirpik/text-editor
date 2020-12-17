import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        List<Integer> lst = Arrays.stream(scanner.nextLine().split("\\s+"))
                            .map(Integer::parseInt)
                            .collect(Collectors.toList());

        List<Integer> odd = new ArrayList<>();
        for (int i = 1; i < lst.size(); i += 2) {
            odd.add(lst.get(i));
        }

        for (int i = odd.size() - 1; i >= 0; i--) {
            System.out.format("%d ", odd.get(i));
        }
        System.out.println();
    }
}