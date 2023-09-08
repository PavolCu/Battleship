package battleship;


import java.util.Arrays;

public class Board {
    private static final int BOARD_SIZE = 10; // Assumed size
    private static final char SHIP_CELL = 'O'; // Assumed character for ship
    private static final char SHIP = 'S';
    private static final char SHIP_HIT = 'X';
    private static final char EMPTY = '-';

    private char[][] board;
    private int shipParts;  // Count of all ship parts on the board.
    private  char[][] shipTypeBoard = new char[BOARD_SIZE][BOARD_SIZE]; // Store type of ship


    public  Board() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            Arrays.fill(board[i], '~');
        }
    }

    public char getCellAt(Coordinate coord) {
        return board[coord.getRow()][coord.getCol()];
    }

    public void setCellAt(Coordinate coord, char value) {
        board[coord.getRow()][coord.getCol()] = value;
    }

    // In Board.java
    public boolean checkIfShipIsSunk(Coordinate start, Coordinate end) {
        if (start.getRow() == end.getRow()) {
            return checkVertically(start, end);
        } else {
            return checkHorizontally(start, end);
        }
    }

    private boolean checkVertically(Coordinate start, Coordinate end) {
        for (int y = start.getCol(); y <= end.getCol(); y++) {
            if (board[start.getRow()][y] != SHIP_HIT) {
                return false;
            }
        }
        return true;
    }

    private boolean checkHorizontally(Coordinate start, Coordinate end) {
        for (int x = start.getRow(); x <= end.getRow(); x++) {
            if (board[x][start.getCol()] != SHIP_HIT) {
                return false;
            }
        }
        return true;
    }

    public boolean placeShip(Coordinate start, Coordinate end, String shipName) {
        if (!isValidPlacement(start, end)) {
            return false; //Ship placement is invalid.
        }

        if (start.getRow() == end.getRow()) {
            for (int y = start.getCol(); y <= end.getCol(); y++) {
                board[start.getRow()][y] = SHIP;
                shipParts++;  // Increment ship parts count.
            }
        } else {
            for (int x = start.getCol(); x <= end.getCol(); x++) {
                board[x][start.getCol()] = SHIP;
                shipParts++;  // Increment ship parts count.
            }
        }
        return true; //Ship placement is successful.
    }

    public void markShipHit(Coordinate hit) {
        if (board[hit.getRow()][hit.getCol()] == SHIP) {
            board[hit.getRow()][hit.getCol()] = SHIP_HIT;
            shipParts--;  // Decrement ship parts count.
        }
    }
    // Check if all ships are sunk.
    public boolean allShipsSunk() {
        return shipParts == 0;
    }

    private boolean isValidPlacement(Coordinate start, Coordinate end) {
        // ... other checks ...

        return !doesShipOverlap(start, end);
    }

    private boolean doesShipOverlap(Coordinate start, Coordinate end) {
        if (start.getRow() == end.getRow()) {
            for (int y = start.getCol(); y <= end.getCol(); y++) {
                if (board[start.getCol()][y] == SHIP) {
                    return true;
                }
            }
        } else {
            for (int x = start.getRow(); x <= end.getRow(); x++) {
                if (board[x][start.getCol()] == SHIP) {
                    return true;
                }
            }
        }
        return false;
    }
    private  boolean isTooCloseToAnotherShip(int startX, int startY, int endX, int endY) {
        for (int i = Math.min(startX, endX) - 1; i <= Math.max(startX, endX) + 1; i++) {
            for (int j = Math.min(startY, endY) - 1; j <= Math.max(startY, endY) + 1; j++) {
                if (i >= 0 && i < BOARD_SIZE && j >= 0 && j < BOARD_SIZE && board[i][j] == SHIP_CELL) {
                    return true; // Found a ship cell too close to the proposed location
                }
            }
        }
        return false;
    }

    public void printBoard() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        char rowName = 'A';
        for (char[] row : board) {
            System.out.print(rowName + " ");
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
            rowName++;
        }
    }

    public boolean isShipAtCoordinate(Coordinate targetCoordinate) {
        return board[targetCoordinate.getRow()][targetCoordinate.getCol()] == SHIP_CELL;
    }

    public boolean wasAlreadyShotAt(Coordinate targetCoordinate) {
        char cell = board[targetCoordinate.getRow()][targetCoordinate.getCol()];
        return cell == 'X' || cell == 'O';
    }

}