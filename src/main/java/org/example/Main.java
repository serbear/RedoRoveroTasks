package org.example;

import org.example.enums.ErrorMessagesEnum;
import org.example.enums.GenderEnum;
import org.example.enums.OutputStringPatternEnum;
import org.example.enums.StringEnum;
import org.example.helpers.ValueChecker;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.example.helpers.Exception.getMethodData;

public class Main {

    public static void main(String[] args) {
        try {
            var monthUtils = new MonthUtils();

            // Data preparation.
            Month[] concreteMonths = getConcreteMonths(monthUtils);
            Employee[] employeesCollection = getEmployees().toArray(new Employee[0]);

            // Show employees' salary.
            for (Employee employee : employeesCollection)
                showSalaryData(
                        employee,
                        concreteMonths);

            // Show total salary.
            var totalSalary = SalaryUtils.getTotalSalary(
                    employeesCollection,
                    concreteMonths);
            showTotalSalaryData(totalSalary);

            // Promote Vasya.
            var newVasya = employeesCollection[1].promoteToManager(1);
            var msg = String.format(
                    "%s if %s now! Be envious, mortals!",
                    newVasya.getName(),
                    newVasya.getClass().getSimpleName());
            System.out.println(msg);

        } catch (IllegalArgumentException e) {
            System.out.printf(
                    OutputStringPatternEnum.ERROR.getString(),
                    getMethodData(e),
                    e.getMessage());
        } catch (Exception e) {
            // Catch any other exceptions and print the stack trace.
            System.out.println(StringEnum.UNHANDLED_EXCEPTION.getString());
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        } finally {
            System.out.println(StringEnum.DONE.getString());
        }
    }

    /**
     * Generates and returns a list of {@link Employee} objects, including both regular employees
     * and managers.
     *
     * @return A {@link List} of {@link Employee} objects containing both regular employees and
     * managers. The list is guaranteed to be non-null but may be empty if no employees are added.
     * @see Employee
     * @see Manager
     * @see GenderEnum
     */
    private static List<Employee> getEmployees() {
        final int MANAGER_PERCENT = 1;
        final int DIRECTOR_PERCENT = 3;

        List<Employee> result = new ArrayList<>();

        // Способ создания сотрудника "Раз".
        // "Простой" конструктор.

        var johnDoeWorker = new Employee(
                "John Doe",
                10,
                GenderEnum.MALE,
                BigDecimal.valueOf(10.10));

        // Способ создания сотрудника "Два".
        // "Цепной" вызов методов.

        var vasyaSmithWorker = new Employee("Vasya Smith")
                .setGender(GenderEnum.MALE)
                .setAge(20)
                .setSalary(BigDecimal.valueOf(20.20));

        var angryManager = new Manager(
                "Blue Beaver",
                90,
                GenderEnum.FEMALE,
                BigDecimal.valueOf(99.99),
                2,
                MANAGER_PERCENT);

        var director = new Manager(
                "Deerector",
                190,
                GenderEnum.MALE,
                BigDecimal.valueOf(999.99),
                3,
                DIRECTOR_PERCENT);

        result.add(johnDoeWorker);
        result.add(vasyaSmithWorker);
        result.add(angryManager);
        result.add(director);

        return result;
    }

    /**
     * Retrieves an array of {@link Month} objects based on the provided {@link MonthUtils}
     * instance.
     *
     * @param mu The {@link MonthUtils} instance used to retrieve the months. Must not
     *           be {@code null}.
     * @return An array of {@link Month} objects with a length of 2. The first element corresponds
     * to the month retrieved by name ("January"), and the second element corresponds to the month
     * retrieved by index (1). If either month is not found, the corresponding array element will
     * be {@code null}.
     * @throws IllegalArgumentException If the provided {@link MonthUtils} instance is {@code null}.
     */
    private static Month[] getConcreteMonths(MonthUtils mu) {
        if (mu == null) {
            throw new IllegalArgumentException(ErrorMessagesEnum.MONTHUTILS_NULL.getString());
        }

        // Set two months for the data range.
        Month[] result = new Month[2];

        var concreteMonthFirst = mu.getMonth("January");
        var concreteMonthSecond = mu.getMonth(1);
        concreteMonthFirst.ifPresent(m -> result[0] = m);
        concreteMonthSecond.ifPresent(m -> result[1] = m);

        return result;
    }

    /**
     * Displays the total salary data in a formatted manner using the output pattern specified
     * by {@link OutputStringPatternEnum#TOTAL_SALARY}.
     * <p>
     * This method prints the total salary to the standard output stream (console) using
     * a predefined string pattern. The pattern is retrieved from
     * the {@link OutputStringPatternEnum} enumeration.
     *
     * @param totalSalary The total salary to be displayed, provided as a {@link BigDecimal}.
     * @see OutputStringPatternEnum
     * @see BigDecimal
     */
    private static void showTotalSalaryData(BigDecimal totalSalary) {
        System.out.printf(
                OutputStringPatternEnum.TOTAL_SALARY.getString(),
                totalSalary);
    }

    /**
     * Displays the total salary data for a given employee over a specified range of months.
     *
     * @param employee The employee for whom the salary data is to be displayed.
     *                 Must not be null.
     * @param months   An array of months over which the salary is calculated.
     *                 Must not be null or empty.
     * @throws IllegalArgumentException If the employee or months collection is invalid,
     *                                  as determined by the {@link ValueChecker} utility methods.
     * @see ValueChecker#checkEmployee(Employee)
     * @see ValueChecker#checkMonthCollection(Month[])
     * @see Employee#getSalary(Month[])
     * @see OutputStringPatternEnum#EMPLOYEE_SALARY
     */
    private static void showSalaryData(Employee employee, Month[] months) {
        ValueChecker.checkEmployee(employee);
        ValueChecker.checkMonthCollection(months);
        var totalSalary = employee.getSalary(months);
        System.out.printf(
                OutputStringPatternEnum.EMPLOYEE_SALARY.getString(),
                employee.getName(),
                totalSalary);
    }
}