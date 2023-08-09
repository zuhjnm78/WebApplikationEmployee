package ru.skypro.webapplikationemployee.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {


    private List<EmployeeByDepartment>employeesByDepartment;
    public DepartmentServiceImpl(){

        employeesByDepartment = new ArrayList<>(List.of(
                new EmployeeByDepartment("Ivan", "Ivanov", 50000, 1),
                new EmployeeByDepartment("Vladimir", "Varlamov",60000,2),
                new EmployeeByDepartment("Ilya", "Vedeneev", 75000, 3),
                new EmployeeByDepartment("Gennadi", "Davydow", 55000, 1),
                new EmployeeByDepartment("Vitaly", "Dolgov", 65000, 2),
                new EmployeeByDepartment("Ivanov", "Gregory", 70000, 3),
                new EmployeeByDepartment("Vladislav", "Kartashov",80000, 4),
                new EmployeeByDepartment("Constantin","Makarov",85000, 4),
                new EmployeeByDepartment("Nikita", "Panfilov",90000, 5)

        ));
    }


    @Override
    public List<EmployeeByDepartment> getEmployeesByDepartment(int departmentId) {
        return employeesByDepartment.stream()
                .filter(employee -> employee.getDepartmentId() == departmentId)
                .collect(Collectors.toList());
    }

    @Override
    public int calculateSalarySumByDepartment(int departmentId) {
        return getEmployeesByDepartment(departmentId).stream()
                .mapToInt(EmployeeByDepartment::getSalary)
                .sum();
    }

    @Override
    public int findMaxSalaryByDepartment(int departmentId) {
        List<EmployeeByDepartment> employeesByDepartment = getEmployeesByDepartment(departmentId);
        OptionalInt maxSalary = employeesByDepartment.stream()
                .mapToInt(EmployeeByDepartment::getSalary)
                .max();
        return maxSalary.orElse(0);
    }

    @Override
    public int findMinSalaryByDepartment(int departmentId) {
        List<EmployeeByDepartment> employeesByDepartment = getEmployeesByDepartment(departmentId);
        OptionalInt minSalary = employeesByDepartment.stream()
                .mapToInt(EmployeeByDepartment::getSalary)
                .min();
        return minSalary.orElse(0);
    }

    @Override
    public Map<Integer, List<EmployeeByDepartment>> getEmployeesGroupedByDepartment() {
        return employeesByDepartment.stream()
                .collect(Collectors.groupingBy(EmployeeByDepartment::getDepartmentId));
    }
}
