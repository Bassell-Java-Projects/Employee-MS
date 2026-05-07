package org.gentleninja.week3employeems.model;

public class Employee<T> implements Comparable<Employee<T>> {
    private T employeeId;
    private String fullname;
    private String department;
    private double salary;
    private double performanceRating;
    private int yearsOfExperience;
    private boolean isActive;

    public Employee() {
    }

    public Employee(T employeeId, String fullname, String department, double salary, double performanceRating, int yearsOfExperience, boolean isActive) {
        this.employeeId = employeeId;
        this.fullname = fullname;
        this.department = department;
        this.salary = salary;
        this.performanceRating = performanceRating;
        this.yearsOfExperience = yearsOfExperience;
        this.isActive = isActive;
    }

    public T getId() {
        return employeeId;
    }

    public void setEmployeeId(T employeeId) {
        this.employeeId = employeeId;
    }



    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getPerformanceRating() {
        return performanceRating;
    }

    public void setPerformanceRating(double performanceRating) {
        this.performanceRating = performanceRating;
    }

    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public int compareTo(Employee<T> other) {
        return Integer.compare(other.getYearsOfExperience(), this.getYearsOfExperience());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", fullname='" + fullname + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                ", performanceRating=" + performanceRating +
                ", yearsOfExperience=" + yearsOfExperience +
                ", isActive=" + isActive +
                '}';
    }

    public String getFullname() {
        return this.fullname;
    }

    public String getFullName() {
        return this.fullname;
    }
}
