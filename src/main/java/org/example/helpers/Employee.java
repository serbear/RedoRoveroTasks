package org.example.helpers;

import java.math.BigDecimal;

public class Employee {
    public static <T extends Number> BigDecimal getAsBigDecimal(T value) {
        return BigDecimal.valueOf(value.doubleValue());
    }
}
