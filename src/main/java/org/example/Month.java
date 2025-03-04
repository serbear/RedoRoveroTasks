package org.example;

import org.example.enums.ErrorMessagesEnum;
import org.example.helpers.ValueChecker;

import java.util.List;

/**
 * Represents a month with a name, total number of days, and number of working days.
 * This class provides methods to access the month's name, total days, and working days.
 */
public class Month {
    private final String name;
    private final int totalDays;
    private final Integer workDays;

    /**
     * Constructs a new {@code Month} object with the specified name, total number of days, and
     * number of working days.
     *
     * @param name      The name of the month. Must be a valid month name as
     *                  per {@link ValueChecker#checkMonthNames(List)}.
     * @param totalDays The total number of days in the month. Must be a valid number of days as
     *                  per {@link ValueChecker#checkMonthDays(List)}.
     * @param workDays  The number of working days in the month. Must be a non-negative integer.
     * @throws IllegalArgumentException If the number of working days is negative.
     *                                  <p>If the month name or total number of days is invalid as
     *                                  per the checks performed by {@link ValueChecker}. </p>
     */
    public Month(String name, int totalDays, int workDays) {
        ValueChecker.checkMonthNames(List.of(name));
        ValueChecker.checkMonthDays(List.of(totalDays));
        if (workDays < 0) {
            throw new IllegalArgumentException(
                    ErrorMessagesEnum.INVALID_WORK_DAY_NUMBER.getString());
        }

        this.name = name;
        this.totalDays = totalDays;
        this.workDays = workDays;
    }

    public String getName() {
        return name;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public Integer getWorkDays() {
        return workDays;
    }
}
