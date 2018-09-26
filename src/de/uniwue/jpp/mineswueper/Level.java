package de.uniwue.jpp.mineswueper;

public class Level {

    public static final Level Beginner = new Level(9, 9, 10, "Beginner");
    public static final Level Intermediate = new Level(16, 16, 40, "Intermediate");
    public static final Level Expert = new Level(30, 16, 99, "Expert");

    private final int width, height, mineCount;
    private final String label;

    private Level(int width, int height, int mineCount, String label) {
        this.width = width;
        this.height = height;
        this.mineCount = mineCount;
        this.label = label;
    }

    public Level(int width, int height, int mineCount) {
        this(width, height, mineCount, "Custom");
    }

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getMineCount() {
        return this.mineCount;
    }

    public String getLabel() {
        return this.label;
    }

    @Override
    public String toString() {
        return String.format("%s:(%dx%d) %d mines", this.label, this.width, this.height, this.mineCount);
    }

    public static void main(String[] args) {
        System.out.println(Level.Beginner);
        System.out.println(Level.Intermediate);
        System.out.println(Level.Expert);
    }
}
