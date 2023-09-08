package battleship;


import java.util.Arrays;

public class Board {
    private static final int BOARD_SIZE = 10; // Assumed size
    private static final char SHIP_CELL = 'O'; // Assumed character for ship
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
        //Normalize the start and end coordinates so that start is always "smaller"
        int startRow = Math.min(start.getRow(), end.getRow());
        int endRow = Math.max(start.getRow(), end.getRow());
        int startCol = Math.min(start.getCol(), end.getCol());
        int endCol = Math.max(start.getCol(), end.getCol());

        //Check if the placement is valid and not too close to another ship
        if (!isValidPlacement(new Coordinate(startRow, startCol), new Coordinate(endRow, endCol)) ||
        isTooCloseToAnotherShip(startRow, startCol, endRow, endCol)) {
            return false; //Ship placement is invalid
        }

        char shipSymbol = shipName.charAt(0); //Assuming unique initials for each ship, e.g. 'A' for Aircraft Barrier, 'B' for Battleship, etc.


        if (startCol == endCol) {
            for (int row = startRow; row <= endRow; row++) {
                board[row][startCol] = SHIP_CELL;
                shipTypeBoard[row][startCol] = shipSymbol; // Store ship type
                shipParts++; // Increment ship parts count.
            }
        } else {
            for (int col = startCol; col <= endCol; col++) {
                board[startRow][col] = SHIP_CELL;
                shipTypeBoard[startRow][col] = shipSymbol; // Store ship type
                shipParts++; // Increment ship parts count.
            }
        }
        return true; // Ship placement is successful.
    }

    public void markShipHit(Coordinate hit) {
        if (board[hit.getRow()][hit.getCol()] == SHIP_CELL) {
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
                if (board[start.getRow()][y] == SHIP_CELL) {
                    return true;
                }
            }
        } else {
            for (int x = start.getRow(); x <= end.getRow(); x++) {
                if (board[x][start.getCol()] == SHIP_CELL) {
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