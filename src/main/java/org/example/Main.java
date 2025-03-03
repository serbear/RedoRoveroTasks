package org.example;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        var monthUtils = new MonthUtils();

        Month[] concreteMonths = getConcreteMonths(monthUtils);
        Employee[] employeesCollection = getEmployees().toArray(new Employee[0]);

        // Salary by an employee
        for (Employee employee : employeesCollection) showSalaryData(employee, concreteMonths);

        // Total salary
        var totalSalary = SalaryUtils.getTotalSalary(employeesCollection, concreteMonths);
        showTotalSalaryData(totalSalary);
    }

    private static void showTotalSalaryData(BigDecimal totalSalary) {
        final String OUTPUT_PATTERN = "Total salary: %s%n";
        System.out.printf(OUTPUT_PATTERN, totalSalary);
    }

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

    private static Month[] getConcreteMonths(MonthUtils mu) {
        Month[] result = new Month[2];

        var concreteMonthFirst = mu.getMonth("January");
        var concreteMonthSecond = mu.getMonth(1);

        concreteMonthFirst.ifPresent(m -> result[0] = m);
        concreteMonthSecond.ifPresent(m -> result[1] = m);

        return result;
    }

    private static void showSalaryData(Employee employee, Month[] months) {
        final String OUTPUT_PATTERN = "Salary for %s: %s%n";
        var totalSalary = employee.getSalary(months);
        System.out.printf(OUTPUT_PATTERN, employee.getName(), totalSalary);
    }

}