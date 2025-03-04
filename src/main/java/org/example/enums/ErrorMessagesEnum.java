package org.example.enums;

/**
 * Enum representing various error messages used throughout the application.
 * Each constant in this enum corresponds to a specific error message that can be used when
 * validating various entities such as employees, months, and their properties.
 */
public enum ErrorMessagesEnum {
    EMPLOYEES_NULL("employees cannot be null"),
    MONTH_NULL("month cannot be null"),
    EMPLOYEE_COLLECTION_EMPTY("The employee collection is empty."),
    MONTH_COLLECTION_EMPTY("The month collection is empty."),
    MONTHUTILS_NULL("MonthUtils must not be null"),
    MONTH_NAME_EMPTY("The month name cannot be empty."),
    MONTH_COLLECTION_DAY_NUMBER("The size of the months days array does not equals 12."),
    MONTH_COLLECTION_NAMES_NUMBER("The size of the months names array does not equals 12."),
    MONTH_COLLECTION_SIZE("The size of the months names array does not equals 12."),
    INVALID_MONTH_DAY_NUMBER("Invalid month day number."),
    EMPTY_MONTH_NAME("The month name cannot be empty."),
    INVALID_MONTH_INDEX("Invalid month index."),
    INVALID_WORK_DAY_NUMBER("The number of a month working days cannot be less than 0."),
    EMPTY_EMPLOYEE_NAME("The employee name cannot be empty."),
    SALARY_LESS_ZERO("The salary cannot be less than zero."),
    GENDER_NULL("Gender cannot be null."),
    AGE_LESS_ZERO("Age cannot be less than 0."),
    INVALID_SUBORDINATES_NUMBER("The subordinates number is invalid."),
    INVALID_SALARY_PERCENT("The salary supplement percent is invalid.");

    private final String displayName;

    ErrorMessagesEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getString() {
        return displayName;
    }
}
