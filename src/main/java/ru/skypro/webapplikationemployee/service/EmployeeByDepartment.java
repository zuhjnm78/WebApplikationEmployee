package ru.skypro.webapplikationemployee.service;

import java.util.Objects;

public class EmployeeByDepartment {
    private String firstName;
    private String lastName;
    private int Salary;
    private Integer DepartmentId;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getSalary() {
        return Salary;
    }

    public Integer getDepartmentId() {
        return DepartmentId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    public void setDepartmentId(Integer departmentId) {
        DepartmentId = departmentId;
    }

    public EmployeeByDepartment() {

    }

    public EmployeeByDepartment(String firstName, String lastName, int salary, Integer departmentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        Salary = salary;
        DepartmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeeByDepartment)) return false;
        EmployeeByDepartment that = (EmployeeByDepartment) o;
        return Salary == that.Salary && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(DepartmentId, that.DepartmentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, Salary, DepartmentId);
    }
}
