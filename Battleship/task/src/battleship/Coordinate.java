package battleship;


public class Coordinate {
    private int row;
    private int col;

    public Coordinate(String coord) {
        if (!coord.matches("[A-J][1-9](?:0)?")) {
            throw new IllegalArgumentException("Invalid coordinate!");
        }
        this.row = coord.charAt(0) - 'A';
        this.col = Integer.parseInt(coord.substring(1)) -1;

    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
