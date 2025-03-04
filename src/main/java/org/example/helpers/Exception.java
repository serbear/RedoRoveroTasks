package org.example.helpers;

/**
 * The {@code Exception} class provides utility methods for handling and processing
 * exception-related data.
 */
public class Exception {
    /**
     * Retrieves and formats the method data from the first element of the stack trace
     * of the provided {@link Throwable} object. The method data includes the class name,
     * method name, and line number where the exception occurred.
     *
     * <p>If the stack trace is not empty, the method constructs a string in the format:
     * <pre>
     * "Source: [className].[methodName] ([lineNumber]). "
     * </pre>
     * If the stack trace is empty, an empty string is returned.
     *
     * @param e The {@link Throwable} object from which to extract the stack trace information.
     *          Must not be {@code null}.
     * @return A formatted string containing the class name, method name, and line number
     * of the first stack trace element, or an empty string if the stack trace is empty.
     * @throws NullPointerException If the provided {@link Throwable} object is {@code null}.
     */
    public static String getMethodData(Throwable e) {
        var result = new StringBuilder();
        StackTraceElement[] stackTrace = e.getStackTrace();
        if (stackTrace.length > 0) {
            StackTraceElement firstElement = stackTrace[0];
            String className = firstElement.getClassName();
            String methodName = firstElement.getMethodName();
            int lineNumber = firstElement.getLineNumber();
            result.append("Source: ").append(className).append(".").append(methodName).append(" (")
                    .append(lineNumber).append("). ");
        }
        return result.toString();
    }
}
