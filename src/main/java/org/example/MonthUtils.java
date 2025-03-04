package org.example;

import org.example.enums.ErrorMessagesEnum;
import org.example.helpers.ValueChecker;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * The {@code MonthUtils} class provides utility methods for managing and retrieving month-related
 * information. It initializes a collection of {@link Month} objects and allows retrieval of month
 * data based on the month name.
 *
 * <p>This class is designed to handle month names, the number of days in each month, and the
 * number of working days in each month. It ensures consistency and validity of the input data
 * during initialization.</p>
 *
 * @see Month
 */
public class MonthUtils {
    private final Month[] monthCollection = new Month[12];
    private final List<String> monthNames = Arrays.asList(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December");
    private final List<Integer> monthDays = Arrays.asList(
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
    private final List<Integer> monthWorkingDays = Arrays.asList(
            17, 20, 21, 22, 18, 19, 23, 21, 22, 23, 19, 22);

    /**
     * Constructs a new {@code MonthUtils} instance and initializes the month collection.
     * This constructor calls the {@code InitializeMonthCollection()} method to set up
     * the internal data structure used for storing and managing month-related information.
     *
     * @see #InitializeMonthCollection()
     */
    public MonthUtils() {
        InitializeMonthCollection();
    }

    /**
     * Initializes the {@code monthCollection} array by creating {@code Month} objects for each
     * month in the {@code monthNames} array. Each {@code Month} object is populated with the
     * corresponding month name, number of days, and number of working days from
     * the {@code monthNames}, {@code monthDays}, and {@code monthWorkingDays} lists, respectively.
     *
     * @throws IllegalArgumentException If the input arrays are inconsistent or invalid,
     *                                  as determined by {@link
     *                                  ValueChecker#checkMonthArrays(Month[], List, List)}
     * @see Month
     * @see ValueChecker#checkMonthArrays(Month[], List, List)
     */
    private void InitializeMonthCollection() {
        ValueChecker.checkMonthArrays(monthCollection, monthNames, monthDays);

        for (String monthName : monthNames) {
            var monthIndex = monthNames.indexOf(monthName);
            var monthRecord = new Month(
                    monthName,
                    monthDays.get(monthIndex),
                    monthWorkingDays.get(monthIndex));
            this.monthCollection[monthIndex] = monthRecord;
        }
    }

    /**
     * Retrieves the {@link Month} corresponding to the specified month index.
     *
     * @param monthIndex the index of the month to retrieve, where 1 represents the first month and
     *                   12 represents the twelfth month.
     * @return an {@link Optional} containing the corresponding {@link Month} if the index is
     * valid (1-12), otherwise an empty {@link Optional}.
     * @throws IllegalArgumentException if the {@code monthIndex} is not valid as determined
     *                                  by {@link ValueChecker#checkMonthIndex(int)}.
     */
    public Optional<Month> getMonth(int monthIndex) {
        ValueChecker.checkMonthIndex(monthIndex);
        if (monthIndex >= 1 && monthIndex <= 12) return Optional.of(monthCollection[monthIndex]);
        return Optional.empty();
    }

    /**
     * Retrieves the {@link Month} object corresponding to the given month name.
     * The method performs a case-insensitive comparison to match the month name.
     *
     * @param monthName The name of the month to retrieve. Must not be empty.
     * @return An {@link Optional} containing the matched {@link Month} object if found,
     * or an empty {@link Optional} if no match is found.
     * @throws IllegalArgumentException If the provided month name is empty.
     */
    public Optional<Month> getMonth(String monthName) {
        if (monthName.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessagesEnum.MONTH_NAME_EMPTY.getString());
        }
        return Arrays.stream(monthCollection)
                .filter(m -> m.getName().equalsIgnoreCase(monthName))
                .findFirst();
    }
}
