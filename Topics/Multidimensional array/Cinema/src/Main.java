import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        // Read the number of rows and seats
        int n = scanner.nextInt();
        int m = scanner.nextInt();

        // Create a 2D matrix to store the seat information
        int[][] seats = new int[n][m];

        // Read the seat information
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                seats[i][j] = scanner.nextInt();
            }
        }

        // Read the number of tickets to buy
        int k = scanner.nextInt();

        // Check if k consecutive seats are available in any row
        for (int i = 0; i < n; i++) {
            int consecutiveSeats = 0;
            for (int j = 0; j < m; j++) {
                if (seats[i][j] == 0) {
                    consecutiveSeats++;
                    if (consecutiveSeats == k) {
                        System.out.println(i + 1); // Output the row number (add 1 to match the problem's output format)
                        return;
                    }
                } else {
                    consecutiveSeats = 0;
                }
            }
        }

        // No row with k consecutive available seats found
        System.out.println(0);
    }
}