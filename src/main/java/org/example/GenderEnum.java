package org.example;

public enum GenderEnum {
    MALE("Male"),
    FEMALE("Female");

    private final String displayName;

    GenderEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
