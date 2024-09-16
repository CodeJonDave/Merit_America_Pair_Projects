package com.techelevator;

import java.time.LocalDate;
import java.util.List;

public class Project {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate dueDate;
    private List<Employee> teamMembers;

    public Project(String name, String description, LocalDate startDate, LocalDate dueDate) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setTeamMembers(List<Employee> teamMembers) {
        this.teamMembers = teamMembers;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return  description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public List<Employee> getTeamMembers() {
        return teamMembers;
    }
}
