package battleship;

public abstract class Ship {
    private String name;
    private int size;

    protected Ship(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public static Ship getShipInstance(String type) {
        switch (type) {
            case "Aircraft Carrier":
                return new AircraftCarrier();
            case "Battleship":
                return new Battleship();
            case "Submarine":
                return new Submarine();
            case "Cruiser":
                return new Cruiser();
            case "Destroyer":
                return new Destroyer();
            default:
                throw new IllegalArgumentException("Invalid ship type");
        }
    }

    public static class AircraftCarrier extends Ship {
        private static final String NAME = "Aircraft Carrier";
        private static final int SIZE = 5;

        public AircraftCarrier() {
            super(NAME, SIZE);
        }
    }

    public static class Battleship extends Ship {
        private static final String NAME = "Battleship";
        private static final int SIZE = 4;

        public Battleship() {
            super(NAME, SIZE);
        }
    }

    public static class Submarine extends Ship {
        private static final String NAME = "Submarine";
        private static final int SIZE = 3;

        public Submarine() {
            super(NAME, SIZE);
        }
    }

    public static class Cruiser extends Ship {
        private static final String NAME = "Cruiser";
        private static final int SIZE = 3;

        public Cruiser() {
            super(NAME, SIZE);
        }
    }

    public static class Destroyer extends Ship {
        private static final String NAME = "Destroyer";
        private static final int SIZE = 2;

        public Destroyer() {
            super(NAME, SIZE);
        }
    }
}



