package org.example;

import org.example.enums.ErrorMessagesEnum;
import org.example.enums.GenderEnum;
import org.example.helpers.ValueChecker;

import java.math.BigDecimal;

/**
 * Represents an employee with attributes such as name, age, gender, and salary.
 * This class provides methods to retrieve and modify employee details.
 */
public class Employee {
    private final String name;
    private int age;
    private GenderEnum sex;
    private BigDecimal wage;

    /**
     * Constructs an Employee object with the specified name.
     *
     * @param name the name of the employee; must not be null or empty.
     * @throws IllegalArgumentException if the name is null or empty.
     */
    public Employee(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException(ErrorMessagesEnum.EMPTY_EMPLOYEE_NAME.getString());
        this.name = name;
    }

    /**
     * Constructs a new Employee instance with the specified name, age, gender, and salary.
     *
     * @param name   the name of the employee; must not be null or empty.
     * @param age    the age of the employee.
     * @param gender the gender of the employee; must not be null.
     * @param salary the salary of the employee; must be a valid value checked
     *               by {@code ValueChecker.checkSalary}.
     * @throws IllegalArgumentException if salary is invalid.
     */
    public Employee(String name, int age, GenderEnum gender, BigDecimal salary) {
        this(name);
        ValueChecker.checkSalary(salary);
        this.age = age;
        this.sex = gender;
        this.wage = salary;
    }

    /**
     * Calculates the total salary for the given array of months.
     *
     * @param monthArray an array of {@code Month} objects representing the months to calculate
     *                   the salary for; must not be null or contain null values.
     * @return the total salary as a {@code BigDecimal}, representing the sum of salaries for all
     * provided months.
     * @throws IllegalArgumentException if the provided month array is null or contains invalid
     *                                  values.
     */
    public BigDecimal getSalary(Month[] monthArray) {
        ValueChecker.checkMonthCollection(monthArray);

        BigDecimal salaryOfMonths = BigDecimal.ZERO;

        for (Month month : monthArray) {
            var workDaysAsDecimal = BigDecimal.valueOf(month.getWorkDays());
            var monthSalary = this.wage.multiply(workDaysAsDecimal);
            salaryOfMonths = salaryOfMonths.add(monthSalary);
        }
        return salaryOfMonths;
    }

    /**
     * Promotes the current employee to a Manager role with the specified number of subordinates.
     *
     * @param numberOfSubordinates The number of subordinates the new manager will oversee.
     *                             Must be a positive integer.
     * @return A new {@code Manager} instance representing the promoted employee.
     * @throws IllegalArgumentException If the {@code numberOfSubordinates} is less than 0.
     */
    public Manager promoteToManager(int numberOfSubordinates) {
        if (numberOfSubordinates < 0)
            throw new IllegalArgumentException(
                    ErrorMessagesEnum.INVALID_SUBORDINATES_NUMBER.getString());
        return new Manager(this, numberOfSubordinates);
    }

    /**
     * Sets the gender of the employee.
     *
     * @param value the gender to set; must not be {@code null}.
     * @return the current {@code Employee} instance for method chaining.
     * @throws IllegalArgumentException if the provided gender is {@code null}.
     */
    public Employee setGender(GenderEnum value) {
        if (value == null)
            throw new IllegalArgumentException(ErrorMessagesEnum.GENDER_NULL.getString());
        this.sex = value;
        return this;
    }

    /**
     * Sets the salary of the employee after validating the provided value.
     *
     * @param value the new salary to be set; must be a valid {@code BigDecimal} as
     *              per {@code ValueChecker.checkSalary}.
     * @return the current {@code Employee} instance with the updated salary.
     * @throws IllegalArgumentException if the provided salary value is invalid.
     */
    public Employee setSalary(BigDecimal value) {
        ValueChecker.checkSalary(value);
        this.wage = value;
        return this;
    }

    /**
     * Sets the age of the employee.
     *
     * @param value the age to set.
     * @return the updated {@code Employee} instance.
     * @throws IllegalArgumentException if the provided age is less than zero.
     */
    public Employee setAge(int value) {
        if (age < 0)
            throw new IllegalArgumentException(ErrorMessagesEnum.AGE_LESS_ZERO.getString());
        this.age = value;
        return this;
    }

    public int getAge() {
        return age;
    }

    public GenderEnum getGender() {
        return this.sex;
    }

    public BigDecimal getWage() {
        return wage;
    }


    public String getName() {
        return this.name;
    }
}
