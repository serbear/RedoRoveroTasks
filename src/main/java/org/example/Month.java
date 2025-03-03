package org.example;

public class Month {

    private final String name;
    private final int totalDays;
    private final Integer workDays;

    public Month(String name, int totalDays, int workDays) {
        this.name = name;
        this.totalDays = totalDays;
        this.workDays = workDays;
    }

    public String getName() {
        return name;
    }

    public int getTotalDays() {
        return totalDays;
    }

    public Integer getWorkDays() {
        return workDays;
    }
}
