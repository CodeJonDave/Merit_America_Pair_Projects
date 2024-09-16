package com.techelevator;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {
    List<Department> departments = new ArrayList<>();
    List<Employee> employees = new ArrayList<>();
    Map<String, Project> projects = new HashMap<>();
    NumberFormat numberformat = NumberFormat.getCurrencyInstance();
    String formattedCurrency;

    /**
     * The main entry point in the application
     *
     * @param args
     */
    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }

    private void run() {
        // create some departments
        createDepartments();

        // print each department by name
        printDepartments();

        // create employees
        createEmployees();

        // give Angie a 10% raise, she is doing a great job!
        giveRaise("Smith, Angie", 10);
        
        // print all employees
        printEmployees();

        // create the TEams project
        createTeamsProject();

        // create the Marketing Landing Page Project
        createLandingPageProject();

        // print each project name and the total number of employees on the project
        printProjectsReport();
    }

    /**
     * Create departments and add them to the collection of departments
     */
    private void createDepartments() {
        Department marketing = new Department(1, "Marketing");
        Department sales = new Department(2, "Sales");
        Department engineering = new Department(3, "Engineering");
        departments.add(marketing);
        departments.add(sales);
        departments.add(engineering);
    }

    /**
     * Print out each department in the collection.
     */
    private void printDepartments() {
        System.out.println("------------- DEPARTMENTS ------------------------------");
        for (Department department : departments) {
            System.out.println(department.getName());
        }
    }

    /**
     * Create employees and add them to the collection of employees
     */
    private void createEmployees() {
        LocalDate today = LocalDate.now();
        Employee deanJohnson = new Employee();
        deanJohnson.setEmployeeId(1);
        deanJohnson.setFirstName("Dean");
        deanJohnson.setLastName("Johnson");
        deanJohnson.setEmail("djohnson@teams.com");
        deanJohnson.setDepartment(getDepartmentByName("Engineering"));
        deanJohnson.setHireDate(today);
        deanJohnson.setSalary(60000.0);

        Employee angieSmith = new Employee(2,
                "Angie",
                "Smith",
                "asmith@teams.com",
                getDepartmentByName("Engineering"),
                today);

        Employee margaretThompson = new Employee(3,
                "Margaret",
                "Thompson",
                "mthompson@teams.com",
                getDepartmentByName("Marketing"),
                today);
        employees.add(deanJohnson);
        employees.add(angieSmith);
        employees.add(margaretThompson);
    }

    /**
     * Print out each employee in the collection.
     */
    private void printEmployees() {
        System.out.println("\n------------- EMPLOYEES ------------------------------");
        for (Employee employee : employees) {
            formattedCurrency = numberformat.format(employee.getSalary());
            System.out.println(
                    employee.getFullName() + " (" +
                            formattedCurrency + ") " +
                            employee.getDepartment().getName());
        }
    }

    /**
     * Create the 'TEams' project.
     */
    private void createTeamsProject() {
        LocalDate today = LocalDate.now();
        Project teams = new Project(
                "TEams",
                "Project Management Software",
                today,
                today.plusDays(30));

        List<Employee> projectEmployees = new ArrayList<>();

        for (Employee employee : employees) {
            if (employee.getDepartment().getName().equals("Engineering")) {
                projectEmployees.add(employee);
            }
            teams.setTeamMembers(projectEmployees);
        }
        projects.put(teams.getName(), teams);
    }

    /**
     * Create the 'Marketing Landing Page' project.
     */
    private void createLandingPageProject() {
        LocalDate start = LocalDate.now().plusDays(31);
        Project landingPage = new Project(
                "Marketing Landing Page",
                "Lead Capture Landing Page for Marketing",
                start,
                start.plusDays(7));

        List<Employee> projectEmployees = new ArrayList<>();

        for (Employee employee : employees) {
            if (employee.getDepartment().getName().equals("Marketing")) {
                projectEmployees.add(employee);
            }
            landingPage.setTeamMembers(projectEmployees);
        }
        projects.put(landingPage.getName(), landingPage);
    }

    /**
     * Print out each project in the collection.
     */
    private void printProjectsReport() {
        System.out.println("\n------------- PROJECTS ------------------------------");
        for (Map.Entry<String, Project> entry : projects.entrySet()) {
            System.out.printf("%s: %d \n", entry.getKey(), entry.getValue().getTeamMembers().size());
        }
    }

    private Department getDepartmentByName(String empDepartment) {
        for (Department department : departments) {
            if (department.getName().equals(empDepartment)) {
                return department;
            }
        }
        return null;
    }

    private void giveRaise(String fullName, int percent) {
        for (Employee employee : employees) {
            if (employee.getFullName().equals(fullName)) {
                employee.giveRaise(percent);
            }
        }
    }


}

