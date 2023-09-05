import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        // Read the size of the matrix
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // Read the matrix elements
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // Find the maximum element and its indexes
        int maxElement = matrix[0][0];
        int maxRowIndex = 0;
        int maxColIndex = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] > maxElement) {
                    maxElement = matrix[i][j];
                    maxRowIndex = i;
                    maxColIndex = j;
                }
            }
        }

        // Output the result
        System.out.println(maxRowIndex + " " + maxColIndex);
    }
}










