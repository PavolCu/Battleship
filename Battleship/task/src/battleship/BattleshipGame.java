package battleship;

import java.util.Scanner;
import java.util.Arrays;

public class BattleshipGame {
    private static final char EMPTY_CELL = '~';
    private static final char SHIP_CELL = 'O';
    private static final char HIT_CELL = 'X';
    private static final char MISS_CELL = 'M';

    private static final int BOARD_SIZE = 10;
    private static final char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
    private static final char[][] fogOfWarBoard = new char[BOARD_SIZE][BOARD_SIZE];
    private static final Scanner scanner = new Scanner(System.in);
    private static final String[] ships = {"Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer"};
    private static final int[] shipSizes = {5, 4, 3, 3, 2};
    private static final int[] shipHits = new int[ships.length];
    private static final int totalShipCells = Arrays.stream(shipSizes).sum();
    private static int totalHits = 0;
    private static final char[][] shipTypeBoard = new char[BOARD_SIZE][BOARD_SIZE];

    public static void main(String[] args) {
        initializeBoard();
        printBoard();


        for (int i = 0; i < ships.length; i++) {
            System.out.println("Enter the coordinates of the " + ships[i] + " (" + shipSizes[i] + " cells):" );
            System.out.print("> ");


            while (true) {
                String input = scanner.nextLine().toUpperCase();
                String[] coordinates = input.split(" ");
                if (coordinates.length != 2) {
                    System.out.println("Error! Incorrect coordinate format. Try again:");
                    continue;
                }

                if (placeShip(coordinates[0], coordinates[1], shipSizes[i], ships[i])) {
                    printBoard();
                    break;
                } else {
                    System.out.print("> ");
                }
            }
        }

        System.out.println("The game starts!");
        System.out.println();
        printFogOfWarBoard();  // This line prints the empty battlefield
        System.out.println("Take a shot!");
        while (true) {

            System.out.print("> ");

            String input = scanner.nextLine().toUpperCase();
            int shotResult = shoot(input);
            printFogOfWarBoard(); // prints board with HIT, MISS, or both

            switch (shotResult) {
                case 0 -> System.out.println("Error! You entered wrong coordinates! Try again:");
                case 1 -> System.out.println("You missed! Try again: ");
                // End the program here
                case 2 -> {
                }
                case 3 -> {
                    printBoardWithMisses(); //prints fog board with all ships hit
                    System.out.println("You sank the last ship. You won. Congratulation!");
                    return;
                }
            }
        }
    }


    public static int recordHit(int shipIndex) {
        // Ensure the ship index is valid
        if (shipIndex < 0 || shipIndex >= ships.length) {
            throw new IllegalArgumentException("Invalid ship index");
        }

        // Ensure the ship isn't already sunk
        if (shipHits[shipIndex] < shipSizes[shipIndex]) {
            shipHits[shipIndex]++;
            totalHits++;

            // Optionally: Check if the ship is now sunk
            if (shipHits[shipIndex] == shipSizes[shipIndex]) {
                return 1;
            }
            return 0;
        }
        return -1;
        }
    private static void initializeBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = EMPTY_CELL;
                fogOfWarBoard[i][j] = EMPTY_CELL;
                shipTypeBoard[i][j] =' ';
            }
        }
    }
    private static void printBoard() {
        printBoardGeneric(board);
        }

    private static void printFogOfWarBoard() {
        printBoardGeneric(fogOfWarBoard);
    }
    private static void printBoardWithMisses() {
        char[][] boardToPrint = new char[BOARD_SIZE][BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (fogOfWarBoard[i][j] == MISS_CELL) {
                    boardToPrint[i][j] = MISS_CELL;
                } else {
                    boardToPrint[i][j] = board[i][j];
                }
            }
        }

        printBoardGeneric(boardToPrint);
    }

    private static boolean placeShip(String start, String end, int shipSize, String shipName) {
        int startX = start.charAt(0) - 'A';
        int startY = Integer.parseInt(start.substring(1)) - 1;
        int endX = end.charAt(0) - 'A';
        int endY = Integer.parseInt(end.substring(1)) - 1;

        // Validating coordinates
        if (isTooCloseToAnotherShip(startX, startY, endX, endY)) {
            System.out.println("Error! You placed it too close to another one. Try again:");
            return false;
        }


        if (startX > BOARD_SIZE - 1 || startY > BOARD_SIZE - 1 || endX > BOARD_SIZE - 1 || endY > BOARD_SIZE - 1) {
            System.out.println("Error! Coordinates outside the board. Try again:");
            return false;
        }

        // Checking if placing vertically or horizontally
        if (startX == endX || startY == endY) {
            if ((startX == endX && Math.abs(endY - startY) + 1 != shipSize) ||
                    (startY == endY && Math.abs(endX - startX) + 1 != shipSize)) {
                System.out.println("Error! Wrong length of the " + shipName + "! Try again:");
                return false;
            }

            int fromI = (startX == endX) ? startX : Math.min(startX, endX);
            int toI = (startX == endX) ? startX : Math.max(startX, endX);

            int fromJ = (startY == endY) ? startY : Math.min(startY, endY);
            int toJ = (startY == endY) ? startY : Math.max(startY, endY);

            for (int i = fromI; i <= toI; i++) {
                for (int j = fromJ; j <= toJ; j++) {
                    if (board[i][j] == SHIP_CELL) {
                        System.out.println("Error! You placed it too close to another one. Try again:");
                        return false;
                    }
                    board[i][j] = SHIP_CELL;
                    shipTypeBoard[i][j] = shipName.charAt(0); //Storing the first char of ship name for simplicity. Idealy, you want to store the index or a ship ID.
                }
            }
        } else {
            System.out.println("Error! Wrong ship location! Try again:");
            return false;
        }
        return true;
    }


    private static boolean isTooCloseToAnotherShip(int startX, int startY, int endX, int endY) {
        for (int i = Math.min(startX, endX) - 1; i <= Math.max(startX, endX) + 1; i++) {
            for (int j = Math.min(startY, endY) - 1; j <= Math.max(startY, endY) + 1; j++) {
                if (i >= 0 && i < BOARD_SIZE && j >= 0 && j < BOARD_SIZE && board[i][j] == SHIP_CELL) {
                    return true; // Found a ship cell too close to the proposed location
                }
            }
        }
        return false;
    }
    private static int shoot(String coord) {
        int x = coord.charAt(0) - 'A';
        int y;

        if (coord.length() < 2 || x < 0 || x >= BOARD_SIZE) {
            return 0;  // Invalid coordinate
        }

        try {
            y = Integer.parseInt(coord.substring(1)) - 1;
        } catch (NumberFormatException e) {
            return 0;  // Invalid coordinate
        }

        // Check if y coordinate is valid
        if (y < 0 || y >= BOARD_SIZE) {
            return 0;  // Invalid coordinate
        }

        if (board[x][y] == SHIP_CELL) {
            board[x][y] = HIT_CELL;
            fogOfWarBoard[x][y] = HIT_CELL;

            char hitShipChar = shipTypeBoard[x][y];
            // Find which type of ship was hit
            int shipTypeIndex = -1;
            for (int i = 0; i < shipSizes.length; i++) {
                if (ships[i].charAt(0) == hitShipChar) {
                    shipTypeIndex = i;
                    break;
                }
            }
            int hitStatus = recordHit(shipTypeIndex);// Invoke the recordHit method here


            if (totalHits == totalShipCells) {
                printBoardWithMisses();
                System.out.println("You sank the last ship. You won. Congratulations! ");
                System.exit(0); //Terminate the program
            } else if (hitStatus == 1) {
                System.out.println("You sank a ship! Specify a new target: ");
            } else if (hitStatus == 0) {
                System.out.println("You hit a ship! Try again: ");
            }
            if (totalHits == totalShipCells) {
                return  3;
            }
            return 2;  // Hit
        } else {
            fogOfWarBoard[x][y] = MISS_CELL;
            return 1;  // Missed
        }
    }

    private static void printBoardGeneric(char[][] boardToPrint) {
        System.out.print("  ");
        for (int i = 1; i <= BOARD_SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print((char) (i + 'A') + " ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(boardToPrint[i][j] + " ");
            }
            System.out.println();
        }
    }
}
