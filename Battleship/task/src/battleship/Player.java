package battleship;
import java.lang.String;

public class Player {
    private String name;
    private Board board;


    // In Player.java
    public enum ShotResult {
        HIT,
        MISS,
        SUNK,
        ALREADY_HIT; // Represents the scenario when a particular cell was already shot at
    }

    public Player(String name) {
        this.name = name;
        this.board = new Board();
    }

    public void setName(String name) {
        this.name = name;
        this.board = board;
    }

    public String getName() {

        return name;
    }


    // Returns true if ship placement is successful, false otherwise
    public boolean placeShip(Coordinate start, Coordinate end, String shipName) {
        return board.placeShip(start, end, shipName);
    }


    public Board getBoard() {

        return this.board;
    }

    public ShotResult takeShot(Coordinate target, Player opponent) {
        Board opponentBoard = opponent.getBoard();

        //If the target cell was already shot at
        if (opponentBoard.wasAlreadyShotAt(target)) {
            return ShotResult.ALREADY_HIT;
        }

        // If there' s ship at the target coordinate
        if (opponentBoard.isShipAtCoordinate(target)) {
            opponentBoard.markShipHit(target); //Mark the cell as hit

            //Check if the entire ship at this coordinate is sunk
            for (String shipName : new String[] {"Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer"}) {
                int shipSize = Ship.getShipInstance(shipName).getSize();
                Coordinate startH = new Coordinate((char) (target.getRow() + 'A') + Integer.toString(Math.max(1, target.getCol() + 1 - shipSize + 1)));
                Coordinate endH = target;
                Coordinate startV = new Coordinate((char) (Math.max(0, target.getRow() - shipSize + 1) + 'A') + Integer.toString(target.getCol() + 1));
                Coordinate endV = target;


                if (opponentBoard.checkIfShipIsSunk(startH, endH) || opponentBoard.checkIfShipIsSunk(startV, endV)) {
                    return ShotResult.SUNK;
                }
            }

            return ShotResult.HIT;
            }else {
            // Mark the cell as a miss and return MISS
            opponentBoard.setCellAt(target, 'M'); //Assume 'M' denotes a missed shot
            return  ShotResult.MISS;
        }
    }

}