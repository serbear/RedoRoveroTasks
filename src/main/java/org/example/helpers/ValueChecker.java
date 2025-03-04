package org.example.helpers;

import org.example.Employee;
import org.example.Month;
import org.example.enums.ErrorMessagesEnum;

import java.math.BigDecimal;
import java.util.List;

/**
 * The {@code ValueChecker} class provides various utility methods for validating data related to
 * managers, employees, salaries, months, and month-related collections.
 * It includes checks for consistency, size, and correctness of the data.
 */
public class ValueChecker {
    private static final int MONTH_COUNT = 12;

    /**
     * Validates the input data for a manager's subordinates and salary supplement percentage.
     *
     * @param subordinateNumber       The number of subordinates the manager oversees.
     * @param salarySupplementPercent The percentage of salary supplement.
     * @throws IllegalArgumentException If subordinateNumber or salarySupplementPercent is less
     *                                  than 0.
     */
    public static void checkManagerData(int subordinateNumber, int salarySupplementPercent) {
        if (subordinateNumber < 0) throw new IllegalArgumentException(
                ErrorMessagesEnum.INVALID_SUBORDINATES_NUMBER.getString());
        if (salarySupplementPercent < 0) throw new IllegalArgumentException(
                ErrorMessagesEnum.INVALID_SALARY_PERCENT.getString());
    }

    /**
     * Checks if the given employee object is null.
     *
     * @param employees The employee object to check.
     * @throws IllegalArgumentException if the employee object is null.
     */
    public static void checkEmployee(Employee employees) {
        if (employees == null)
            throw new IllegalArgumentException(ErrorMessagesEnum.EMPLOYEES_NULL.getString());
    }

    /**
     * Checks if the provided salary is non-negative.
     *
     * @param salary the salary to be checked.
     * @throws IllegalArgumentException if the salary is less than zero.
     * @see BigDecimal#compareTo(BigDecimal)
     */
    public static void checkSalary(BigDecimal salary) {
        if (salary.compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException(ErrorMessagesEnum.SALARY_LESS_ZERO.getString());
    }

    /**
     * Checks the validity of the provided employee collection.
     *
     * @param employees an array of {@link Employee} objects to be validated.
     * @throws IllegalArgumentException if the employees array is null or empty.
     * @see ErrorMessagesEnum#EMPLOYEES_NULL
     * @see ErrorMessagesEnum#EMPLOYEE_COLLECTION_EMPTY
     */
    public static void checkEmployeeCollection(Employee[] employees) {
        if (employees == null)
            throw new IllegalArgumentException(ErrorMessagesEnum.EMPLOYEES_NULL.getString());
        if (employees.length == 0)
            throw new IllegalArgumentException(
                    ErrorMessagesEnum.EMPLOYEE_COLLECTION_EMPTY.getString());
    }

    /**
     * Checks if the provided array of {@link Month} is valid.
     *
     * @param months The array of {@link Month} objects to be checked.
     * @throws IllegalArgumentException If the array is {@code null} or empty.
     */
    public static void checkMonthCollection(Month[] months) {
        if (months == null)
            throw new IllegalArgumentException(ErrorMessagesEnum.MONTH_NULL.getString());
        if (months.length == 0)
            throw new IllegalArgumentException(
                    ErrorMessagesEnum.EMPLOYEE_COLLECTION_EMPTY.getString());
    }

    /**
     * Validates the provided arrays and lists related to months, ensuring that the number of
     * months, their names, and their days all meet specific criteria.
     *
     * @param monthCollection an array of {@link Month} objects representing the months of
     *                        the year.
     * @param monthNames      a {@link List} of {@link String}s representing the names of
     *                        the months.
     * @param monthDays       a {@link List} of {@link Integer}s representing the number of days in
     *                        each month.
     * @throws IllegalArgumentException if any of the checks fail, specific error messages from
     *                                  the {@code ErrorMessagesEnum} will be used.
     */
    public static void checkMonthArrays(
            Month[] monthCollection,
            List<String> monthNames,
            List<Integer> monthDays) {

        checkSize(monthCollection.length, MONTH_COUNT, ErrorMessagesEnum.MONTH_COLLECTION_SIZE);
        checkSize(monthNames.size(), MONTH_COUNT, ErrorMessagesEnum.MONTH_COLLECTION_NAMES_NUMBER);
        checkSize(monthDays.size(), MONTH_COUNT, ErrorMessagesEnum.MONTH_COLLECTION_DAY_NUMBER);

        checkMonthDays(monthDays);

        checkMonthNames(monthNames);

        checkMonthCollection(monthCollection);
    }

    /**
     * Checks whether the actual size matches the expected size.
     *
     * @param actualSize   The actual size to be checked.
     * @param expectedSize The expected size to compare with.
     * @param errorMessage The error message to be used in case of mismatch.
     * @throws IllegalArgumentException If the actual size does not match the expected size.
     */
    @SuppressWarnings("SameParameterValue")
    private static void checkSize(
            int actualSize,
            int expectedSize,
            ErrorMessagesEnum errorMessage) {

        if (actualSize != expectedSize)
            throw new IllegalArgumentException(errorMessage.getString());
    }

    /**
     * Validates that the days in the given list of month days are within the valid range of 28
     * to 31.
     *
     * @param monthDays A list of integers representing the number of days in a month.
     * @throws IllegalArgumentException If any value in the list is less than 28 or greater than 31.
     */
    public static void checkMonthDays(List<Integer> monthDays) {
        if (monthDays.stream().anyMatch(days -> days < 28 || days > 31))
            throw new IllegalArgumentException(
                    ErrorMessagesEnum.INVALID_MONTH_DAY_NUMBER.getString());
    }

    /**
     * Checks if any of the month names in the provided list is empty.
     *
     * @param monthNames The list of month names to check.
     * @throws IllegalArgumentException if any of the month names in the list is an empty string.
     */
    public static void checkMonthNames(List<String> monthNames) {
        if (monthNames.stream().anyMatch(String::isEmpty))
            throw new IllegalArgumentException(ErrorMessagesEnum.EMPTY_MONTH_NAME.getString());
    }

    /**
     * Checks whether the given month index is valid.
     *
     * @param monthIndex The index of the month to be validated.
     * @throws IllegalArgumentException If the month index is less than 0 or greater
     *                                  than {@link #MONTH_COUNT}.
     */
    public static void checkMonthIndex(int monthIndex) {
        if (monthIndex < 0 || monthIndex > MONTH_COUNT)
            throw new IllegalArgumentException(ErrorMessagesEnum.INVALID_MONTH_INDEX.getString());
    }
}
