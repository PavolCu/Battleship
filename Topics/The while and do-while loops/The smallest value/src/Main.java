import java.math.BigInteger;
import java.util.Scanner;


class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        BigInteger m = new BigInteger(scanner.nextLine());
        System.out.println(smallestFactorialGreaterThan(m));
    }

    public static int smallestFactorialGreaterThan(BigInteger m) {
        int n = 1;
        BigInteger factorial = BigInteger.ONE;

        while (factorial.compareTo(m) <= 0) {
            n++;
            factorial = factorial.multiply(BigInteger.valueOf(n));
        }

        return n;
    }
}