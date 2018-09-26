package de.uniwue.jpp.mineswueper;

public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(Coordinate old, int x, int y) {
        this(old.x + x, old.y + y);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    @Override
    public int hashCode() {
        return this.x * this.y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Coordinate) {
            Coordinate c = (Coordinate) obj;
            return c.x == this.x && c.y == this.y;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("(%d/%d)", this.x, this.y);
    }
}
