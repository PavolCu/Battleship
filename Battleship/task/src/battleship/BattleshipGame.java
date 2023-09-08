package battleship;
import java.util.Scanner;

public class BattleshipGame {
    private final Scanner scanner = new Scanner(System.in);
    private final Player player1 = new Player("Player 1");
    private final Player player2 = new Player("Player 2");

    private void setupGame() {
        for (int i = 0; i < 2; i++) {
            Player currentPlayer = (i == 0) ? player1 : player2;
            Player opponent = (i == 0) ? player1 : player2;
            System.out.println(currentPlayer.getName() + ", place your ships on the game field");
            currentPlayer.getBoard().printBoard();

            String[] shipNames = {"Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer"};
            for (String shipName : shipNames) {
                boolean shipPlaced = false;

                while (!shipPlaced) {
                    System.out.println("\nEnter the coordinates of the " + shipName + " (" + Ship.getShipInstance(shipName).getSize() + " cells):");
                    String input = scanner.nextLine().trim();
                    String[] parts = input.split(" ");

                    if (parts.length != 2) {
                        System.out.println("Error! Please enter two coordinates separated by a space.");
                        continue;
                    }

                    try {
                        Coordinate initialStart = new Coordinate(parts[0]);
                        Coordinate initialEnd = new Coordinate(parts[1]);
                        Coordinate startCoord;
                        Coordinate endCoord;
                        Coordinate start;
                        Coordinate end;

                        // Check if coordinates are in order and swap if necessary
                        if (initialStart.getRow() > initialEnd.getRow() || (initialStart.getRow() == initialEnd.getRow() && initialStart.getCol() > initialEnd.getCol())) {
                            startCoord = initialEnd;
                            endCoord = initialStart;
                        } else {
                            startCoord = initialStart;
                            endCoord = initialEnd;
                        }

                        if (startCoord.getRow() == endCoord.getRow()) {
                            // Horizontal placement
                            start = new Coordinate(startCoord.getRow(), Math.min(startCoord.getCol(), endCoord.getCol()));
                            end = new Coordinate(startCoord.getRow(), Math.max(startCoord.getCol(), endCoord.getCol()));
                        } else if (startCoord.getCol() == endCoord.getCol()) {
                            // Vertical placement
                            start = new Coordinate(Math.min(startCoord.getRow(), endCoord.getRow()), startCoord.getCol());
                            end = new Coordinate(Math.max(startCoord.getRow(), endCoord.getRow()), startCoord.getCol());
                        } else {
                            System.out.println("Error! Ship coordinates must be aligned horizontally or vertically. Try again.");
                            continue;
                        }
                        // Check if the ship size matchech the provided coordinates
                        int expectedSize = Ship.getShipInstance(shipName).getSize();
                        int actualSize = Math.abs(end.getRow() - start.getRow() + Math.abs(end.getCol()) - start.getCol()) + 1;
                        if (expectedSize != actualSize) {
                            System.out.println("Error! Wrong length of the " + shipName + "! Try again:");
                            continue;
                        }

                        // Try to place the ship
                        if (currentPlayer.placeShip(start, end, shipName)) {
                            shipPlaced = true;
                        } else {
                            System.out.println("Error! Invalid placement. Try again.");
                        }

                        currentPlayer.getBoard().printBoard();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error! " + e.getMessage());
                    }
                }
            }
            clearScreen();

            if (i == 0) {
                System.out.println("\nPress Enter and pass the move to another player...");
                scanner.nextLine();
            } else {
                System.out.println("\n" + opponent.getName() + ", place your ships on the game field");
                currentPlayer.getBoard().printBoard(); //Print player 1 ' board
            }
        }
    }

    public void start() {
        setupGame();
        System.out.println("Starting the game...");
        Player currentPlayer = player1;
        Player opponent = player2;
        while (!gameOver()) {
            System.out.println(currentPlayer.getName() + ", take your shot!");

            Coordinate shotCoordinate = new Coordinate(scanner.nextLine().trim());
            Player.ShotResult result = currentPlayer.takeShot(shotCoordinate, opponent);
            if (result == Player.ShotResult.HIT) {
                System.out.println("That's a hit!");
            } else if (result == Player.ShotResult.MISS) {
                System.out.println("Missed!");
                Player temp = currentPlayer;
                currentPlayer = opponent;
                opponent = temp;
            }
        }
        System.out.println("Game over! Winner: " + (player1.getBoard().allShipsSunk() ? player2.getName() : player1.getName()));
    }

    private boolean gameOver() {
        return player1.getBoard().allShipsSunk() || player2.getBoard().allShipsSunk();
    }

    public static void main(String[] args) {
        BattleshipGame game = new BattleshipGame();
        game.start();
    }

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
