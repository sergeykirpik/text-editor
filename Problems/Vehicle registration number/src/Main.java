import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regNum = scanner.nextLine(); // a valid or invalid registration number

        /* write your code here */
        String regNumberRegex = "\\s*[ABEKMHOPCTYX][0-9]{3}[ABEKMHOPCTYX]{2}\\s*";

        boolean isRegNum = regNum.matches(regNumberRegex);
        System.out.println(isRegNum);
    }
}