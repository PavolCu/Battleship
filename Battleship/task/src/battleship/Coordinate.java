package battleship;

public class Coordinate {
    private int row;
    private int col;

    public Coordinate(int row, int col ) {
        if (row < 0 || row >= 10 || col < 0 || col >= 10) {
            throw new IllegalArgumentException("Invalid coordinate!");
        }
        this.row = row;
        this.col = col;

    }
    public Coordinate(String coordStr) {
        coordStr = coordStr.trim();

        if (coordStr.length() < 2) {
            throw new IllegalArgumentException("Invalid coordinate format!");
        }
        char rowChar = coordStr.charAt(0);
        String colStr = coordStr.substring(1);

        this.row = rowChar - 'A';
        this.col = Integer.parseInt(colStr) - 1;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
