package ru.skypro.webapplikationemployee.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.skypro.webapplikationemployee.exception.EmployeeAlreadyAddedException;
import ru.skypro.webapplikationemployee.exception.EmployeeNotFoundException;
import ru.skypro.webapplikationemployee.exception.EmployeeStorageIsFullException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeServiceImpl();
    }
    @Test
    public void testAddEmployee_Successful() {
        Employee employee = new Employee("Ivan", "Ivanov");
        assertDoesNotThrow(() -> employeeService.addEmployee(employee));
    }


    @Test
    public void testAddEmployee_AlreadyAdded() {
        Employee employee = new Employee("Ivan", "Ivanov");
        employeeService.addEmployee(employee);

        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.addEmployee(employee));
    }

    @Test
    public void testAddEmployee_StorageFull() {
        for (int i = 0; i < EmployeeService.MAX_EMPLOYEES; i++) {
            Employee employee = new Employee("Employee" + i, "LastName" + i);
            employeeService.addEmployee(employee);
        }

        Employee newEmployee = new Employee("New", "Employee");
        assertThrows(EmployeeStorageIsFullException.class, () -> employeeService.addEmployee(newEmployee));
    }

    @Test
    public void testRemoveEmployee_ExistingEmployee() {
        Employee employeeToRemove = new Employee("Ivan", "Ivanov");
        employeeService.addEmployee(employeeToRemove);

        Employee removedEmployee = employeeService.removeEmployee("Ivan", "Ivanov");

        assertNotNull(removedEmployee);
        assertEquals("Ivan", removedEmployee.getFirstName());
        assertEquals("Ivanov", removedEmployee.getLastName());
    }


    @Test
    public void testRemoveEmployee_NonExistingEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.removeEmployee("Ivan", "Ivanov"));
    }


    @Test
    public void findEmployee_ExistingEmployee() {
        Employee expectedEmployee = new Employee("Ivan", "Ivanov");
        Employee foundEmployee = employeeService.findEmployee("Ivan", "Ivanov");

        assertNotNull(foundEmployee);
        assertEquals("Ivan", foundEmployee.getFirstName());
        assertEquals("Ivanov", foundEmployee.getLastName());
        assertEquals(expectedEmployee, foundEmployee);
    }
    @Test
    public void findEmployee_NonExistingEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.findEmployee("Ivan", "Ivanov"));
    }

    @Test
   public void getMaxEmployees() {
        int maxEmployees = employeeService.getMaxEmployees();
        assertEquals(EmployeeService.MAX_EMPLOYEES, maxEmployees);
    }

    @Test
   public void getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        assertEquals(EmployeeService.MAX_EMPLOYEES, employees.size());
    }

}