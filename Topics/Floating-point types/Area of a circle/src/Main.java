import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // put your code here
        double r = scanner.nextDouble();
        double s = Math.PI * Math.pow(r, 2);
        System.out.println(s);
    }
}