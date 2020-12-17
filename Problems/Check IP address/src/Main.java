import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String maybeIP = scanner.nextLine();
        String ipAddressRegex = "((25[0-5]|2[0-4][0-9]|1?[0-9]{1,2})\\.){3}(25[0-5]|2[0-4][0-9]|1?[0-9]{1,2})";

        System.out.println(maybeIP.matches(ipAddressRegex) ? "YES" : "NO");
    }
}
