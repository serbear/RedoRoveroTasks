package org.example.enums;

/**
 * Enum representing a set of predefined string values with associated display names.
 */
public enum StringEnum {
    UNHANDLED_EXCEPTION("Caught an unhandled exception."),
    DONE("Done.");

    private final String displayName;

    StringEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getString() {
        return displayName;
    }
}
