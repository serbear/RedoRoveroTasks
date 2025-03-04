package org.example.helpers;

import java.math.BigDecimal;

/**
 * Utility class for working with numeric values.
 */
public class Numbers {
    /**
     * Converts a {@link Number} to a {@link BigDecimal}.
     *
     * @param value The numeric value to convert, must be of a type that extends {@link Number}.
     * @param <T>   The type of the number, which must extend {@link Number}.
     * @return A {@link BigDecimal} representing the value of the given numeric value.
     * @throws NullPointerException if the provided {@code value} is {@code null}.
     */
    public static <T extends Number> BigDecimal getAsBigDecimal(T value) {
        return BigDecimal.valueOf(value.doubleValue());
    }
}
