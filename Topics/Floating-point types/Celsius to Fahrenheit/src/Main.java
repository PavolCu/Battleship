import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        final double magicOne = 1.8;
        final int magicTwo = 32;
        double c = scanner.nextDouble();
        double f = c * magicOne + magicTwo;
        System.out.println(f);
    }
}