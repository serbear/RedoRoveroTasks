package org.example.enums;

/**
 * Enum representing different output string patterns used for displaying various messages.
 * Each enum constant contains a pattern string with placeholders that can be replaced with actual
 * values.
 * The patterns are designed for use in formatting various output or error messages.
 */
public enum OutputStringPatternEnum {
    TOTAL_SALARY("Total salary: %s%n"),
    EMPLOYEE_SALARY("Salary for %s: %s%n"),
    ERROR("[ERROR] %s%s%n");

    private final String displayName;

    OutputStringPatternEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getString() {
        return displayName;
    }
}
