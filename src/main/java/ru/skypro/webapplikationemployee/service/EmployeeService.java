package ru.skypro.webapplikationemployee.service;

import java.util.List;

public interface EmployeeService {

    void addEmployee(Employee employee);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    int getMaxEmployees();

    List<Employee> getAllEmployees();

}
