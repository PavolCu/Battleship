import java.util.Scanner;

public class Main {

    public static boolean isVowel(char ch) {
        // write your code here
        ch = Character.toLowerCase(ch);
        for (char c : new char[]{'a', 'e', 'i', 'o'}) {
            if (ch == c) {
                return true;
            }
        }
        return ch == 'u';
    }

    /* Do not change code below */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char letter = scanner.nextLine().charAt(0);
        System.out.println(isVowel(letter) ? "YES" : "NO");
    }
}