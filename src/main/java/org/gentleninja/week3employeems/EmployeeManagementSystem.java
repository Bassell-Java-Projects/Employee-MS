package org.gentleninja.week3employeems;

import org.gentleninja.week3employeems.model.Employee;
import org.gentleninja.week3employeems.repository.EmployeeDB;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeManagementSystem {
    static EmployeeDB<Integer> db = new EmployeeDB<>();
    Comparator<Employee<Integer>> EmployeeSalaryComparator = Comparator.comparingDouble((Employee<Integer> e) -> e.getSalary()).reversed();
    Comparator<Employee<Integer>> EmployeePRatingComparator = Comparator.comparingDouble((Employee<Integer> e) -> e.getPerformanceRating()).reversed();

    public static void main(String[] args) {
        db.addEmployee(new Employee<>(1, "Alice Johnson", "HR", 55000, 4.6, 5, true));
        db.addEmployee(new Employee<>(2, "Bob Smith", "IT", 70000, 4.2, 8, true));
        db.addEmployee(new Employee<>(3, "Carol White", "Finance", 64000, 4.8, 4, true));
        db.addEmployee(new Employee<>(4, "David Brown", "IT", 85000, 3.9, 10, true));
        db.addEmployee(new Employee<>(5, "Eva Green", "HR", 53000, 4.9, 2, true));

        showAllEmployees();
    }

    public void giveRasie(int employeeId, double raiseAmount) {
        Employee<Integer> employee = db.getEmployeeById(employeeId);
        if(employee.getPerformanceRating() >= 4.5){
            employee.setSalary(employee.getSalary() + raiseAmount);
        } else {
            System.out.println("Employee does not have minimum performance rating");
        }
    }

    public List<Employee<Integer>> getTop5Paide() {
        return db.getAllEmployees().stream()
                .sorted(Comparator.comparingDouble((Employee<?> e) -> e.getSalary()).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    public double getDepAverage(String dep) {
        return db.searchEmployeeByDepartment(dep).stream()
                .mapToDouble(e -> (int) e.getSalary())
                .sum();
    }

    public static void showAllEmployees() {
        List<Employee<Integer>> allEmployees = db.getAllEmployees();

        System.out.printf("| %-5s | %-20s | %-12s | %-10s | %-12s | %-12s | %-7s |%n"
        ,"ID", "FullName", "Department", "Salary", "Performance", "Experience", "Active");
        System.out.println("_".repeat(100));

        for(Employee<Integer> e: allEmployees){
            System.out.printf("| %-5s | %-20s | %-12s | %-10s | %-12s | %-12s | %-7s |%n",
                    e.getId(),
                    e.getFullname(),
                    e.getDepartment(),
                    e.getSalary(),
                    e.getPerformanceRating(),
                    e.getYearsOfExperience(),
                    e.getIsActive());
        }
    }
}
