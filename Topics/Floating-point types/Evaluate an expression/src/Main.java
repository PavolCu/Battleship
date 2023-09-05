import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        double d = scanner.nextDouble();
        final double magicOne = 10.5;
        final double magicTwo = 4.4;
        final double magicThree = 2.2;
        System.out.println(a * magicOne + b * magicTwo + (c + d) / magicThree);
    }
}