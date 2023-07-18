package ru.skypro.webapplikationemployee.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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
    public ResponseEntity addEmployee(@RequestParam String firstName,
                                      @RequestParam String lastName) {
        if (StringUtils.isAnyBlank(firstName, lastName)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input: first name and last name are required");
        }
        String formattedFirstName = StringUtils.capitalize(firstName);
        String formattedLastName = StringUtils.capitalize(lastName);
        Employee employee = new Employee();
        employee.setFirstName(formattedFirstName);
        employee.setLastName(formattedLastName);
        try {

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
    public ResponseEntity removeEmployee(@RequestParam String firstName,
                                         @RequestParam String lastName) {
        if (StringUtils.isAnyBlank(firstName, lastName)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input: first name and last name are required");
        }
        String formattedFirstName = StringUtils.capitalize(firstName);
        String formattedLastName = StringUtils.capitalize(lastName);
        try {
            Employee employee = employeeService.removeEmployee(formattedFirstName,formattedLastName);
            return ResponseEntity.ok(employee);
        } catch (EmployeeNotFoundException e) {
            System.err.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Сотрудник не найден.");
        }
    }

    @GetMapping("/find")
    @NonNull
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity findEmployee(@RequestParam String firstName,
                                       @RequestParam String lastName) {
        if (StringUtils.isAnyBlank(firstName, lastName)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input: first name and last name are required");
        }
        String formattedFirstName = StringUtils.capitalize(firstName);
        String formattedLastName = StringUtils.capitalize(lastName);
        try {
            Employee employee = employeeService.findEmployee(formattedFirstName, formattedLastName);
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