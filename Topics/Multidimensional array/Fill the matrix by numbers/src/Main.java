import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        if (n > 100) {
            System.out.println("Invalid input. n should not be greater than 100.");
            return;
        }

        int[][] matrix = new int[n][n];

        // Fill the matrix based on the given rule
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int diff = Math.abs(i - j);
                matrix[i][j] = diff;
            }
        }

        // Print the matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}


