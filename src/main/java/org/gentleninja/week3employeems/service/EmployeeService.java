package org.gentleninja.week3employeems.service;

import org.gentleninja.week3employeems.model.Employee;
import org.gentleninja.week3employeems.repository.EmployeeDB;

import java.util.Comparator;
import java.util.List;

public class EmployeeService {
    private static EmployeeDB<Integer> db = new EmployeeDB<>();
    Comparator<Employee<Integer>> EmployeeSalaryComparator = Comparator.comparingDouble((Employee<Integer> e) -> e.getSalary()).reversed();
    Comparator<Employee<Integer>> EmployeePRatingComparator = Comparator.comparingDouble((Employee<Integer> e) -> e.getPerformanceRating()).reversed();
    Comparator<Employee<Integer>> EmployeeExperienceComparator = Comparator.comparingInt((Employee<Integer> e) -> e.getYearsOfExperience()).reversed();

    static {
        db.addEmployee(new Employee<>(1, "Alice Johnson", "HR", 55000, 4.6, 5, true));
        db.addEmployee(new Employee<>(2, "Bob Smith", "IT", 70000, 4.2, 8, true));
        db.addEmployee(new Employee<>(3, "Carol White", "Finance", 64000, 4.8, 4, true));
        db.addEmployee(new Employee<>(4, "David Brown", "IT", 85000, 3.9, 10, true));
        db.addEmployee(new Employee<>(5, "Eva Green", "HR", 53000, 4.9, 2, true));
    }

    public List<Employee<Integer>> getAllEmployees() {
        return db.getAllEmployees();
    }

    public List<Employee<Integer>> searchEmployeeByName(String query) {
        return db.searchEmployeeByName(query);
    }

    public List<Employee<Integer>> searchEmployeeByDepartment(String dept) {
        return db.searchEmployeeByDepartment(dept);
    }

    public List<Employee<Integer>> searchEmployeeByMinRating(double minRating) {
        return db.searchEmployeeByMinRating(minRating);
    }

    public List<Employee<Integer>> sortByName() {
        List<Employee<Integer>> employees = db.getAllEmployees();
        employees.sort(EmployeeSalaryComparator);
        return employees;
    }

    public List<Employee<Integer>> sortBySalary() {
        List<Employee<Integer>> employees = db.getAllEmployees();
        employees.sort(EmployeeSalaryComparator);
        return employees;
    }

    public List<Employee<Integer>> sortByExperience() {
        List<Employee<Integer>> employees = db.getAllEmployees();
        employees.sort(EmployeeExperienceComparator);
        return employees;
    }

    public List<Employee<Integer>> sortByPerformance() {
        List<Employee<Integer>> employees = db.getAllEmployees();
        employees.sort(EmployeePRatingComparator);
        return employees;
    }

    public void addEmployee(Employee<Integer> emp) {
        db.addEmployee(emp);
    }

    public void removeEmployee(Integer id) {
        db.removeEmployee(id);
    }

    public double sumAllSalaries() {
        return db.getAllEmployees().stream()
                .mapToDouble((Employee<Integer> e) -> e.getSalary())
                .sum();
    }
}
