package org.gentleninja.week3employeems.repository;

import org.gentleninja.week3employeems.model.Employee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeDB<T> {
    public final Map<T, Employee<T>> EDB = new HashMap<>();

    public EmployeeDB() {
    }

    public Employee<T> addEmployee(Employee<T> employee) {
        this.EDB.put(employee.getId(), employee);
        return employee;
    }

    public void removeEmployee(T employeeId) {}

    public Employee<T> updateEmployeeDetails(T employeeId, String field, Object newValue) {
        Employee<T> emp = EDB.get(employeeId);
        return emp;
    }

    public List<Employee<T>> getAllEmployees() {
        return EDB.values().stream().toList();
    }

    public Employee<T> getEmployeeById(T id) {
        return this.EDB.get(id);
    }

    public List<Employee<T>> searchEmployeeByDepartment(String department) {
        return EDB.values().stream()
                .filter(employee -> employee.getDepartment().equals(department))
                .collect(Collectors.toList());
    }

    public List<Employee<T>> searchEmployeeByName(String namePart) {
        return EDB.values().stream()
                .filter(employee -> employee.getFullname().contains(namePart))
                .collect(Collectors.toList());
    }

    public List<Employee<T>> searchEmployeeByMinRating(double rating) {
        return EDB.values().stream()
                .filter(employee -> employee.getPerformanceRating() >= rating)
                .collect(Collectors.toList());
    }

    public List<Employee<T>> searchEmployeeBySalaryRange(double sRange, double eRange) {
        return EDB.values().stream()
                .filter(entry -> entry.getSalary() > sRange)
                .filter(entry -> entry.getSalary() < eRange)
                .collect(Collectors.toList());
    }
}
