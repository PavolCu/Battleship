import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        // Step 1: Read matrix dimensions
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // Step 2: Read matrix elements
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // Step 3: Read indexes i and j
        int i = scanner.nextInt();
        int j = scanner.nextInt();

        // Step 4: Swap columns i and j
        for (int row = 0; row < n; row++) {
            int temp = matrix[row][i];
            matrix[row][i] = matrix[row][j];
            matrix[row][j] = temp;
        }

        // Step 5: Print the updated matrix
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}