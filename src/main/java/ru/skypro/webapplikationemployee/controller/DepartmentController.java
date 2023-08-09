package ru.skypro.webapplikationemployee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.webapplikationemployee.service.DepartmentService;
import ru.skypro.webapplikationemployee.service.DepartmentServiceImpl;
import ru.skypro.webapplikationemployee.service.Employee;
import ru.skypro.webapplikationemployee.service.EmployeeByDepartment;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final DepartmentService departmentService;
@Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping("/{id}/employees")
    public ResponseEntity<List<EmployeeByDepartment>> getEmployeesByDepartment(@PathVariable int id) {
        List<EmployeeByDepartment> employees = departmentService.getEmployeesByDepartment(id);
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}/salary/sum")
    public ResponseEntity<Integer> getSalarySumByDepartment(@PathVariable int id) {
        Integer salarySum = departmentService.calculateSalarySumByDepartment(id);
        return ResponseEntity.ok(salarySum);
    }

    @GetMapping("/{id}/salary/max")
    public ResponseEntity<Integer> getMaxSalaryByDepartment(@PathVariable int id) {
        int maxSalary = departmentService.findMaxSalaryByDepartment(id);
        return ResponseEntity.ok(maxSalary);
    }

    @GetMapping("/{id}/salary/min")
    public ResponseEntity<Integer> getMinSalaryByDepartment(@PathVariable int id) {
        int minSalary = departmentService.findMinSalaryByDepartment(id);
        return ResponseEntity.ok(minSalary);
    }

    @GetMapping("/employees")
    public ResponseEntity<Map<Integer, List<EmployeeByDepartment>>> getEmployeesGroupedByDepartment() {
        Map<Integer, List<EmployeeByDepartment>> employeesByDepartment = departmentService.getEmployeesGroupedByDepartment();
        return ResponseEntity.ok(employeesByDepartment);
    }
}
