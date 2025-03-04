package org.example;

import org.example.enums.GenderEnum;
import org.example.helpers.ValueChecker;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.example.helpers.Numbers.getAsBigDecimal;

/**
 * Represents a manager in the company, extending the {@code Employee} class.
 * A manager has a specific number of subordinates and a salary supplement percentage.
 */
public class Manager extends Employee {
    private int subordinateNumber;
    private int salarySupplementPercent;

    /**
     * Constructs a new Manager with the specified name.
     *
     * @param name the name of the manager
     */
    public Manager(String name) {
        super(name);
    }

    /**
     * Constructs a new {@code Manager} instance with the specified attributes.
     *
     * @param name                    The name of the manager.
     * @param age                     The age of the manager.
     * @param gender                  The gender of the manager, represented by {@link GenderEnum}.
     * @param salary                  The base salary of the manager.
     * @param subordinateNumber       The number of subordinates under the manager's supervision.
     * @param salarySupplementPercent The percentage of salary supplement.
     * @throws IllegalArgumentException If {@code subordinateNumber}
     *                                  or {@code salarySupplementPercent}
     *                                  do not meet validation criteria defined
     *                                  in {@link ValueChecker#checkManagerData(int, int)}.
     */
    public Manager(
            String name,
            int age,
            GenderEnum gender,
            BigDecimal salary,
            int subordinateNumber,
            int salarySupplementPercent) {

        super(name, age, gender, salary);

        ValueChecker.checkManagerData(subordinateNumber, salarySupplementPercent);

        this.subordinateNumber = subordinateNumber;
        this.salarySupplementPercent = salarySupplementPercent;
    }

    /**
     * Calculates the total salary for the current employee, considering the normal salary and
     * the supplementary salary based on the number of subordinates and the salary supplement
     * percentage.
     *
     * @param monthArray an array of {@link Month} objects representing the months for which the
     *                   salary is calculated.
     * @return the total salary, including the normal salary and supplementary salary for
     * subordinates, rounded to two decimal places.
     * @throws IllegalArgumentException if any of the months in the {@code monthArray} are invalid.
     */
    public BigDecimal getSalary(Month[] monthArray) {
        ValueChecker.checkMonthCollection(monthArray);

        var normalSalary = super.getSalary(monthArray);
        //noinspection BigDecimalMethodWithoutRoundingCalled
        var percent = getAsBigDecimal(this.salarySupplementPercent).divide(getAsBigDecimal(100));
        var sumOfOneSubordinate = normalSalary.multiply(percent);
        var totalForSubordinates = sumOfOneSubordinate.multiply(
                getAsBigDecimal(this.subordinateNumber));
        var result = normalSalary.add(totalForSubordinates);

        return result.setScale(2, RoundingMode.HALF_UP);
    }
}
