package org.example;

import java.math.BigDecimal;

public class Employee {
    private final String name;
    private int age;
    private GenderEnum sex;
    private BigDecimal wage;

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, int age, GenderEnum gender, BigDecimal salary) {
        // todo: check data values.
        this(name);

        this.age = age;
        this.sex = gender;
        this.wage = salary;
    }

    public BigDecimal getSalary(Month[] monthArray) {

        // todo: empty array.
        // todo: this.salaryPerDay is 0 or null.

        BigDecimal salaryOfMonths = BigDecimal.ZERO;

        for (Month month : monthArray) {
            var workDaysAsDecimal = BigDecimal.valueOf(month.getWorkDays());
            var monthSalary = this.wage.multiply(workDaysAsDecimal);
            salaryOfMonths = salaryOfMonths.add(monthSalary);
        }
        return salaryOfMonths;
    }

    public Employee setGender(GenderEnum value) {
        this.sex = value;
        return this;
    }

    public Employee setSalary(BigDecimal value) {
        this.wage = value;
        return this;
    }

    public Employee setAge(int value) {
        this.age = value;
        return this;
    }

    public String getName() {
        return this.name;
    }
}
