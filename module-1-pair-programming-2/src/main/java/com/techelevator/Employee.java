package com.techelevator;

import java.time.LocalDate;

public class Employee {
    private static final double STARTING_SALARY = 60000.0;
    private long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private double salary;
    Department department;
    private LocalDate hireDate;

    public Employee(
            long employeeId,
            String firstName,
            String lastName,
            String email,
            Department department,
            LocalDate hireDate) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.hireDate = hireDate;
        this.salary = STARTING_SALARY;
    }

    public Employee() {
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public double getSalary() {
        return salary;
    }

    public Department getDepartment() {
        return department;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public String getFullName() {
        return lastName + ", " + firstName;
    }

    public void giveRaise(int percent) {
        this.salary = salary + (salary * percent / 100);
    }
}
