package de.uniwue.jpp.mineswueper;

public class Field {
    private final Coordinate coordinate;
    private boolean flag, mine, opened;
    private int neighbourMineCount;

    public Field (Coordinate coord) {
        this.coordinate = coord;
        this.flag = false;
        this.mine = false;
        this.opened = false;
        this.neighbourMineCount = 0;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public boolean hasFlag() {
        return this.flag;
    }

    public void setHasFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean hasMine() {
        return this.mine;
    }

    public void setHasMine(boolean mine) {
        this.mine = mine;
    }

    public boolean isOpened() {
        return this.opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    public int getNeighbourMineCount() {
        return this.neighbourMineCount;
    }

    public void setNeighbourMineCount(int count) throws IllegalArgumentException {
        if (count < 0) throw new IllegalArgumentException("only positive neighbourMineCounts allowed.");
        this.neighbourMineCount = count;
    }
}
