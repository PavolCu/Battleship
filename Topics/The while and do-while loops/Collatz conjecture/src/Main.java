import java.util.ArrayList;
import java.util.Scanner;

class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Integer> sequence = generateCollatzSequence(n);
        for (int num : sequence) {
            System.out.print(num + " ");
        }
    }

    public static ArrayList<Integer> generateCollatzSequence(int n) {
        ArrayList<Integer> sequence = new ArrayList<>();
        sequence.add(n);
        while (n != 1) {
            if (n % 2 == 0) {
                n = n / 2;
            } else {
                n = n * 3 + 1;
            }
            sequence.add(n);
        }
        return sequence;
    }
}
