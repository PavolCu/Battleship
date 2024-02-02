package battleship;

import java.util.Scanner;

public class Main {
    private static final char FOG = '~';
    private static final char SHIP = 'O';
    private static final int SIZE = 10;
    private static final char[][] board = new char[SIZE][SIZE];

    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        placeAllShips();
    }

    private static void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = FOG;
            }
        }
    }

    private static void printBoard() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        for (int i = 0; i < SIZE; i++) {
            System.out.print((char) ('A' + i) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isAdjacent(int row1, int col1, int row2, int col2) {
        for (int i = row1 - 1; i <= row2 + 1; i++) {
            for (int j = col1 - 1; j <= col2 + 1; j++) {
                if (i >= 0 && j >= 0 && i < SIZE && j < SIZE && board[i][j] == SHIP) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void placeAllShips() {
        placeShip("Aircraft Carrier", 5);
        placeShip("Battleship", 4);
        placeShip("Submarine", 3);
        placeShip("Cruiser", 3);
        placeShip("Destroyer", 2);
    }

    private static void placeShip(String name, int length) {
        Scanner scanner = new Scanner(System.in);
        boolean placed = false;

        while (!placed) {
            System.out.println("Enter the coordinates of the " + name + " (" + length + " cells):");
            System.out.print("> ");
            String[] coordinates = scanner.nextLine().split(" ");
            int row1 = coordinates[0].charAt(0) - 'A';
            int col1 = Integer.parseInt(coordinates[0].substring(1)) - 1;
            int row2 = coordinates[1].charAt(0) - 'A';
            int col2 = Integer.parseInt(coordinates[1].substring(1)) - 1;

            if (row1 > row2 || col1 > col2) {
                int tmpRow = row1;
                int tmpCol = col1;
                row1 = row2;
                row2 = tmpRow;
                col1 = col2;
                col2 = tmpCol;
            }

            if (row1 != row2 && col1 != col2) {
                System.out.println("Error! Wrong ship location! Try again:");
                continue;
            }

            if (Math.abs(row1 - row2) + Math.abs(col1 - col2) != length - 1) {
                System.out.println("Error! Wrong length of the " + name + "! Try again:");
                continue;
            }

            if (isAdjacent(row1, col1, row2, col2)) {
                System.out.println("Error! You placed it too close to another one. Try again:");
                continue;
            }

            boolean overlap = false;
            for (int i = row1; i <= row2; i++) {
                for (int j = col1; j <= col2; j++) {
                    if (board[i][j] == SHIP) {
                        overlap = true;
                        break;
                    }
                }
                if (overlap) break;
            }

            if (overlap) {
                System.out.println("Error! You placed it on top of another one. Try again:");
                continue;
            }

            for (int i = row1; i <= row2; i++) {
                for (int j = col1; j <= col2; j++) {
                    board[i][j] = SHIP;
                }
            }
            placed = true;
        }
        printBoard(); // We only print the board after the ship is placed correctly and after the while loop.
    }
}

