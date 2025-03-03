package org.example;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalaryUtils {
    public static BigDecimal getTotalSalary(Employee[] employees, Month[] months) {
        // todo: empty arrays.
       
        var result = BigDecimal.ZERO;

        for (var employee : employees) {
            var salary = employee.getSalary(months);
            result = result.add(salary);
        }

        return result.setScale(2, RoundingMode.HALF_UP);
    }
}
