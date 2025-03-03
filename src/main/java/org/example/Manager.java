package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.example.helpers.Employee.getAsBigDecimal;

public class Manager extends Employee {

    private int subordinateNumber;
    private int salarySupplementPercent;

    public Manager(String name) {
        super(name);
    }

    public Manager(
            String name,
            int age,
            GenderEnum gender,
            BigDecimal salary,
            int subordinateNumber,
            int salarySupplementPercent) {

        super(name, age, gender, salary);
        this.subordinateNumber = subordinateNumber;
        this.salarySupplementPercent = salarySupplementPercent;
    }

    public BigDecimal getSalary(Month[] monthArray) {
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
