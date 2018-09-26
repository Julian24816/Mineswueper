package de.uniwue.jpp.mineswueper;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {
    private static final int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    private final Map<Coordinate, Field> fields;

    public Board(int width, int height, Collection<Coordinate> mines) {
        // init fields
        this.fields = new HashMap<>();
        for (int x = 0; x < width; x++) for (int y = 0; y < height; y++) {
            Coordinate c = new Coordinate(x, y);
            fields.put(c, new Field(c));
        }

        // save mines
        for (Coordinate c: mines) fields.get(c).setHasMine(true);

        // compute neighbourMineCounts
        int neighbourMineCount;
        for (Field f: fields.values()) {
            neighbourMineCount = 0;
            for (int[] d: dir) if (mines.contains(new Coordinate(f.getCoordinate(), d[0], d[1])))
                neighbourMineCount++;
            f.setNeighbourMineCount(neighbourMineCount);
        }
    }

    public boolean hasWon() {
        for (Field f: fields.values())
            if (f.hasMine() ^ !f.isOpened()) return false;
        return true;
    }

    public void flagField(Coordinate coord) {
        Field field = fields.get(coord);
        if (!field.isOpened()) field.setHasFlag(!field.hasFlag());
    }

    public int getRemainingMines() {
        int count = 0;
        for (Field f: fields.values()) {
            if (f.hasMine()) count++;
            if (f.hasFlag()) count--;
        }
        return count;
    }

    public Collection<Field> getMines() {
        return fields.values().stream().filter(Field::hasMine).collect(Collectors.toList());
    }

    public Collection<Field> getFields() {
        return Collections.unmodifiableCollection(fields.values());
    }

    public RevealFieldsResult revealFields(Coordinate coord) {
        Field field = fields.get(coord);
        if (field.hasFlag() || field.isOpened()) return new RevealFieldsResult();

        field.setOpened(true);
        if (field.hasMine() || field.getNeighbourMineCount()!=0)
            return new RevealFieldsResult(Collections.singleton(field));

        return new RevealFieldsResult(Stream.concat(Stream.of(field), Arrays.stream(dir)
                .map(ints -> new Coordinate(field.getCoordinate(), ints[0], ints[1]))
                .filter(fields::containsKey)
                .map(fields::get)
                .map(field1 -> revealFields(field1.getCoordinate()).getRevealedFields())
                .flatMap(Collection::stream))
                .collect(Collectors.toList()));
    }

    public RevealFieldsResult revealMultiClickFields(Coordinate coord) {
        Field field = fields.get(coord);
        if (!field.isOpened() || field.isOpened() && field.getNeighbourMineCount() == 0)
            return new RevealFieldsResult();

        Collection<Field> neighbourFields = Arrays.stream(dir)
                .map(ints -> new Coordinate(field.getCoordinate(), ints[0], ints[1]))
                .filter(fields::containsKey)
                .map(fields::get)
                .collect(Collectors.toList());

        if (neighbourFields.stream().filter(Field::hasFlag).count() != field.getNeighbourMineCount())
            return new RevealFieldsResult();

        return new RevealFieldsResult(neighbourFields.stream()
                .filter(field1 -> !field1.hasFlag())
                .map(field1 -> revealFields(field1.getCoordinate()).getRevealedFields())
                .flatMap(Collection::stream)
                .collect(Collectors.toList()));
    }
}
