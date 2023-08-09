package ru.skypro.webapplikationemployee.service;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    List<EmployeeByDepartment> getEmployeesByDepartment(int departmentId);

    int calculateSalarySumByDepartment(int departmentId);

    int findMaxSalaryByDepartment(int departmentId);

    int findMinSalaryByDepartment(int departmentId);

    Map<Integer, List<EmployeeByDepartment>> getEmployeesGroupedByDepartment();
}
