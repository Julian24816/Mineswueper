package de.uniwue.jpp.mineswueper;

import java.util.Collection;
import java.util.LinkedList;

public class RevealFieldsResult {

    private final Collection<Field> revealedFields;

    public enum RevealFieldState {
        FIELD_NOT_REVEALED, FIELDS_REVEALED, FOUND_MINE
    }

    public RevealFieldsResult() {
        this.revealedFields = new LinkedList<>();
    }

    public RevealFieldsResult(Collection<Field> fields) {
        this.revealedFields = fields;
    }

    public Collection<Field> getRevealedFields() {
        return this.revealedFields;
    }

    public RevealFieldState getState() {
        if (this.revealedFields.isEmpty()) return RevealFieldState.FIELD_NOT_REVEALED;
        for (Field f : this.revealedFields) {
            if (f.hasMine()) return RevealFieldState.FOUND_MINE;
        }
        return RevealFieldState.FIELDS_REVEALED;
    }
}
