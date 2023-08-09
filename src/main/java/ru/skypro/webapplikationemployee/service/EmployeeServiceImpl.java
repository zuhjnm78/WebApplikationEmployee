package ru.skypro.webapplikationemployee.service;

import org.springframework.stereotype.Service;
import ru.skypro.webapplikationemployee.exception.EmployeeAlreadyAddedException;
import ru.skypro.webapplikationemployee.exception.EmployeeNotFoundException;
import ru.skypro.webapplikationemployee.exception.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.List;
@Service

public class EmployeeServiceImpl implements EmployeeService {
    private Employee employee = new Employee();

    public List<Employee> employees;
    private static final int MAX_EMPLOYEES = 50;

    public EmployeeServiceImpl() {
        employees = new ArrayList<>(List.of(
                new Employee("Ivan", "Ivanov"),
                new Employee("Vladimir", "Varlamov"),
                new Employee("Ilya", "Vedeneev"),
                new Employee("Gennadi", "Davydow"),
                new Employee("Vitaly", "Dolgov"),
                new Employee("Ivanov", "Gregory"),
                new Employee("Vladislav", "Kartashov"),
                new Employee("Constantin","Makarov"),
                new Employee("Nikita", "Panfilov"),
                new Employee("Pavel","Teterin")
        ));
    }

    @Override
    public void addEmployee(Employee employee) {
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Сотрудник "
                    + employee.getLastName() + " " + employee.getFirstName() + " уже добавлен.");
        }

        if(employees.size() < MAX_EMPLOYEES) {
        employees.add(employee);
        System.out.println("Сотрудник успешно добавлен.");
    } else {
        throw new EmployeeStorageIsFullException("Достигнуто максимальное количество сотрудников.");
    }

    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        if (employees.remove(employee)) {
            System.out.println("Сотрудник успешно удален.");
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден.");
        }
        return null;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Сотрудник не найден.");
    }

    @Override
    public int getMaxEmployees() {

        return MAX_EMPLOYEES;
    }

    @Override
    public List<Employee> getAllEmployees() {

        return employees;
    }

}
