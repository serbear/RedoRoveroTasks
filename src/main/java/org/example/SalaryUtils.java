package org.example;

import org.example.helpers.ValueChecker;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Utility class for working with employee salary.
 * Provides methods to perform various operations related to salary calculation.
 */
public class SalaryUtils {
    /**
     * Returns the total of all employees' salaries for the specified months.
     *
     * @param employees Array of employees for whom the total salary is calculated.
     *                  Each employee-object must implement the method {@code getSalary(Month[])}.
     * @param months    An array of months for which the salary is calculated.
     *                  If the array is empty, no salary calculation is performed.
     * @return Total sum of salaries of all employees for the specified months rounded to two
     * decimal places using rounding mode {@link RoundingMode#HALF_UP}.
     * @throws IllegalArgumentException If the employee or month array has no elements or is equal
     *                                  to {@code null}.
     */
    public static BigDecimal getTotalSalary(Employee[] employees, Month[] months) {
        ValueChecker.checkMonthCollection(months);
        ValueChecker.checkEmployeeCollection(employees);

        var result = BigDecimal.ZERO;

        for (var employee : employees) {
            var salary = employee.getSalary(months);
            result = result.add(salary);
        }

        return result.setScale(2, RoundingMode.HALF_UP);
    }
}
