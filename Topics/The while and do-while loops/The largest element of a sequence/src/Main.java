import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int largest = Integer.MIN_VALUE;
        int number;
        number = scanner.nextInt();
        while (number != 0) {
            if (number > largest) {
                largest = number;
            }
            number = scanner.nextInt();
        }
        System.out.println(largest);
        scanner.close();
    }
}