import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double densityOfLiquid = scanner.nextDouble();
        final double g = 9.8;
        double h = scanner.nextDouble();
        double p = densityOfLiquid * g * h;
        System.out.println(p);

    }
}
