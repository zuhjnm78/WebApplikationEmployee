package ru.skypro.webapplikationemployee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import ru.skypro.webapplikationemployee.exception.EmployeeNotFoundException;
import ru.skypro.webapplikationemployee.service.Employee;
import ru.skypro.webapplikationemployee.exception.EmployeeAlreadyAddedException;
import ru.skypro.webapplikationemployee.exception.EmployeeStorageIsFullException;
import ru.skypro.webapplikationemployee.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    @NonNull
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity addEmployee(@RequestParam(required = false) String firstName,
                                      @RequestParam(required = false) String lastName) {
        if (firstName == null || lastName == null) {
            return ResponseEntity.badRequest().body("Both 'firstName' and 'lastName' parameters are required.");
        }
        try {
            Employee employee = new Employee(firstName, lastName);
            employeeService.addEmployee(employee);
            return ResponseEntity.ok(employee);
        } catch (EmployeeStorageIsFullException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Достигнуто максимальное количество сотрудников.");
        } catch (EmployeeAlreadyAddedException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Такой сотрудник уже существует.");
        }
    }

    @GetMapping("/remove")
    @NonNull
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity removeEmployee(@RequestParam(required = false) String firstName,
                                                   @RequestParam(required = false) String lastName) {
        if (firstName == null || lastName == null) {
            return ResponseEntity.badRequest().body("Both 'firstName' and 'lastName' parameters are required.");
        }
        try {
            Employee employee = employeeService.removeEmployee(firstName, lastName);
            return ResponseEntity.ok(employee);
        } catch (EmployeeNotFoundException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Сотрудник не найден.");
        }
    }

    @GetMapping("/find")
    @NonNull
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity findEmployee(@RequestParam(required = false) String firstName,
                                                 @RequestParam(required = false) String lastName) {
        if (firstName == null || lastName == null) {
            return ResponseEntity.badRequest().body("Both 'firstName' and 'lastName' parameters are required.");
        }
        try {
            Employee employee = employeeService.findEmployee(firstName, lastName);
            return ResponseEntity.ok(employee);
        } catch (EmployeeNotFoundException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Сотрудник не найден.");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();

        if (employees.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(employees);
        }
    }
}