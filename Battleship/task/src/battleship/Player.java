package battleship;
import java.util.List;


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
        if (opponent.getBoard().wasAlreadyShotAt(target)) {
            return ShotResult.ALREADY_HIT;
        } else if (opponent.getBoard().isShipAtCoordinate(target)) {
            opponent.getBoard().setCellAt(target, 'X');
            // Assume 'X' denotes a hit cell
            for (String shipName : new String[] {"Aircraft Carrier", "Battleship", "Submarine", "Cruiser", "Destroyer"}) {
                int shipSize = Ship.getShipInstance(shipName).getSize();
                Coordinate startH = new Coordinate((char) (target.getRow() + 'A') + Integer.toString(target.getCol() + 1 - shipSize + 1));
                Coordinate endH = target;
                Coordinate startV = new Coordinate((char) (target.getRow() - shipSize + 'A' + 1) + Integer.toString(target.getCol() + 1));
                Coordinate endV = target;

                if (opponent.getBoard().checkIfShipIsSunk(startH, endH) || opponent.getBoard().checkIfShipIsSunk(startV, endV)) {
                    return ShotResult.SUNK;
                }
            }
            return ShotResult.HIT;
        } else {
            opponent.getBoard().setCellAt(target, 'O'); // Assume 'O' denotes a missed shot
            return ShotResult.MISS;
        }
    }

}