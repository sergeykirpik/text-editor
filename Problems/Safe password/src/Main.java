import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String password = scanner.nextLine().trim();

        boolean containsUpperCaseLetter = password.matches(".*[A-Z].*");
        boolean containsLowerCaseLetter = password.matches(".*[a-z].*");
        boolean containsDigit = password.matches(".*[0-9].*");

        boolean isStrong = containsUpperCaseLetter
                && containsLowerCaseLetter
                && containsDigit
                && password.length() >= 12;

        System.out.println(isStrong ? "YES" : "NO");
    }
}