import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        // Read the values of a, b, and c
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();

        // Solve the equation and find the value of x
        double x = (c - b) / a;

        // Output the value of x
        System.out.println(x);

        scanner.close();
    }
}