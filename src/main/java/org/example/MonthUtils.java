package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MonthUtils {
    private final Month[] monthCollection = new Month[12];
    private final List<String> monthNames = Arrays.asList(
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December");
    private final List<Integer> monthDays = Arrays.asList(
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
    private final List<Integer> monthWorkingDays = Arrays.asList(
            17, 20, 21, 22, 18, 19, 23, 21, 22, 23, 19, 22);

    public MonthUtils() {
        InitializeMonthCollection();
    }

    private void InitializeMonthCollection() {
        for (String monthName : monthNames) {
            var monthIndex = monthNames.indexOf(monthName);
            var monthRecord = new Month(monthName,
                    monthDays.get(monthIndex),
                    monthWorkingDays.get(monthIndex));
            this.monthCollection[monthIndex] = monthRecord;
        }
    }

    public Optional<Month> getMonth(int monthIndex) {
        if (monthIndex >= 1 && monthIndex <= 12) return Optional.of(monthCollection[monthIndex]);
        return Optional.empty();
    }

    public Optional<Month> getMonth(String monthName) {
        return Arrays.stream(monthCollection)
                .filter(m -> m.getName().equalsIgnoreCase(monthName))
                .findFirst();
    }
}
