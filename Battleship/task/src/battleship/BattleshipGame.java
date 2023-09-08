package battleship;
import java.lang.String;


import java.util.Scanner;


public class BattleshipGame {
    private Scanner scanner = new Scanner(System.in);
    private Player player1 = new Player("Player 1");
    private Player player2 = new Player("Player 2");


    private void setupGame() {


        for (int i = 0; i < 2; i++) {
            Player currentPlayer = (i == 0) ? player1 : player2;
            System.out.println(currentPlayer.getName() + ", place your ships on the game field");
            currentPlayer.getBoard().printBoard();

            String[] shipNames = {"Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer"};
            for (String shipName : shipNames) {
                boolean shipPlaced = false;

                while (!shipPlaced) {
                    System.out.println("\nEnter the coordinates of the " + shipName + " (" + Ship.getShipInstance(shipName).getSize() +" cells):");
                    String input = scanner.nextLine().trim();
                    String[] parts = input.split(" ");

                    if (parts.length != 2) {
                        System.out.println("Error! Please enter two coordinates separated by a space.");
                        continue;
                    }

                    Coordinate startCoord = new Coordinate(parts[0]);
                    Coordinate endCoord = new Coordinate(parts[1]);

                    // Try to place the ship
                    if (currentPlayer.placeShip(startCoord, endCoord, shipName)) {
                        shipPlaced = true;
                    } else {
                        System.out.println("Error! Invalid placement. Try again.");
                    }

                    currentPlayer.getBoard().printBoard();
                }
            }
            clearScreen();

            System.out.println("\nPress Enter and pass the move to another player...");
            scanner.nextLine();
        }
    }


    public void start() {

        player1 = new Player("Player 1");
        player2= new Player("Player 2");
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
            } else {
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
