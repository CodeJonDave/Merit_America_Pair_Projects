package com.techelevator.hr;

import com.techelevator.Billable;
import com.techelevator.Person;

import java.util.Map;

public class Employee extends Person implements Billable {

    private int employeeId;
    private String title;
    private Department department;
    private double salary;

    private final double EMPLOYEE_DISCOUNT = 0.5;

    public Employee(String firstName, String lastName) {
        this(firstName, lastName, "", 0);
    }

    public Employee(String firstName, String lastName, String title, double salary) {
        super(firstName,lastName);
        this.title = title;
        this.salary = salary;
    }

    @Override
    public String getFullName() {
        return this.getLastName() + ", " + this.getFirstName();
    }

    public void raiseSalary(double percentage) {
        if( percentage > 0) {
            this.salary += salary * percentage / 100;
        }
    }


    // getters and setters

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public double getBalanceDue(Map<String, Double> servicesRendered) {
        double subtotal = 0;
        for(Map.Entry<String,Double> e : servicesRendered.entrySet()){
            if (e.getKey().equals("Walking")){
                subtotal += e.getValue() * EMPLOYEE_DISCOUNT;
            } else {
                subtotal += e.getValue();
            }
        }
        return subtotal;
    }
}
