package ru.skypro.webapplikationemployee.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DepartmentServiceTest {
    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentServiceImpl departmentServiceImplUnderTest;

    @Test
    void getEmployeesByDepartment() {
        when(departmentService.getEmployeesByDepartment(1)).thenReturn(Arrays.asList(
                new EmployeeByDepartment("Ivan", "Ivanov", 50000, 1),
                new EmployeeByDepartment("Semen", "Smirnov", 60000, 2),
                new EmployeeByDepartment("Kiril", "Kirilov", 45000, 1),
                new EmployeeByDepartment("Michail", "Michailov", 55000, 1)
        ));
        List<EmployeeByDepartment> employees = departmentServiceImplUnderTest.getEmployeesByDepartment(1);

        assertEquals(3, employees.size());
    }

    @Test
    void calculateSalarySumByDepartment() {
        when(departmentService.getEmployeesByDepartment(1)).thenReturn(Arrays.asList(
                new EmployeeByDepartment("Ivan", "Ivanov", 50000, 1),
                new EmployeeByDepartment("Semen", "Smirnov", 60000, 2),
                new EmployeeByDepartment("Kiril", "Kirilov", 45000, 1),
                new EmployeeByDepartment("Michail", "Michailov", 55000, 1)
        ));

        int totalSalary = departmentServiceImplUnderTest.calculateSalarySumByDepartment(1);

        assertEquals(150000, totalSalary);
    }

    @Test
    void findMaxSalaryByDepartment() {
        when(departmentService.getEmployeesByDepartment(1)).thenReturn(Arrays.asList(
                new EmployeeByDepartment("Ivan", "Ivanov", 50000, 1),
                new EmployeeByDepartment("Semen", "Smirnov", 60000, 2),
                new EmployeeByDepartment("Kiril", "Kirillov", 45000, 1),
                new EmployeeByDepartment("Michail", "Michailov", 55000, 1)
        ));
        int maxSalary = departmentServiceImplUnderTest.findMaxSalaryByDepartment(1);

        assertEquals(55000, maxSalary);
    }

    @Test
    void findMinSalaryByDepartment() {
        when(departmentService.getEmployeesByDepartment(1)).thenReturn(Arrays.asList(
                new EmployeeByDepartment("Ivan", "Ivanov", 50000, 1),
                new EmployeeByDepartment("Semen", "Smirnov", 60000, 2),
                new EmployeeByDepartment("Kiril", "Kirillov", 45000, 1),
                new EmployeeByDepartment("Michail", "Michailov", 55000, 1)
        ));
        int minSalary = departmentServiceImplUnderTest.findMinSalaryByDepartment(1);

        assertEquals(45000, minSalary);
    }

    @Test
    void getEmployeesGroupedByDepartment() {
        when(departmentService.getEmployeesByDepartment(1)).thenReturn(Arrays.asList(
                new EmployeeByDepartment("Ivan", "Ivanov", 50000, 1),
                new EmployeeByDepartment("Semen", "Smirnov", 60000, 2),
                new EmployeeByDepartment("Kiril", "Kirillov", 45000, 1),
                new EmployeeByDepartment("Michail", "Michailov", 55000, 1)
        ));
        Map<Integer, List<EmployeeByDepartment>> employeesByDepartment = departmentServiceImplUnderTest.getEmployeesGroupedByDepartment();

        assertNotNull(employeesByDepartment);
        assertEquals(2, employeesByDepartment.size());
        assertEquals(3, employeesByDepartment.get(1).size());
        assertEquals(1, employeesByDepartment.get(2).size());
    }
    @Test
    public void testGetEmployeesGroupedByDepartment_EmptyList() {
        // Замокаем EmployeeService для возврата пустого списка сотрудников
        when(departmentService.getEmployeesByDepartment(1)).thenReturn(Arrays.asList());

        Map<Integer, List<EmployeeByDepartment>> employeesByDepartment = departmentServiceImplUnderTest.getEmployeesGroupedByDepartment();

        assertNotNull(employeesByDepartment);
        assertTrue(employeesByDepartment.isEmpty());
    }

    @Test
    public void testGetEmployeesGroupedByDepartment_WithNullDepartment() {
        // Замокаем EmployeeService для возврата списка сотрудников с нулевыми номерами отделов
        when(departmentService.getEmployeesByDepartment(1)).thenReturn(Arrays.asList(
                new EmployeeByDepartment("Artem", "Ivanov", 50000, null),
                new EmployeeByDepartment("Artur", "Smirnov", 60000, null)
        ));

        Map<Integer, List<EmployeeByDepartment>> employeesByDepartment = departmentServiceImplUnderTest.getEmployeesGroupedByDepartment();

        assertNotNull(employeesByDepartment);
        assertEquals(1, employeesByDepartment.size());
        assertEquals(2, employeesByDepartment.get(null).size());
    }

    @Test
    public void testGetEmployeesGroupedByDepartment_WithNullDepartment_EmptyList() {
        // Замокаем EmployeeService для возврата пустого списка сотрудников с нулевыми номерами отделов
        when(departmentService.getEmployeesByDepartment(1)).thenReturn(Arrays.asList(
                new EmployeeByDepartment("Artem", "Ivanov", 50000, null),
                new EmployeeByDepartment("Artur", "Smirnov", 60000, null)
        ));

        Map<Integer, List<EmployeeByDepartment>> employeesByDepartment = departmentServiceImplUnderTest.getEmployeesGroupedByDepartment();

        assertNotNull(employeesByDepartment);
        assertEquals(1, employeesByDepartment.size());
        assertTrue(employeesByDepartment.get(null).isEmpty());
    }

    @Test
    public void testGetEmployeesGroupedByDepartment_WithMixedDepartments() {
        when(departmentService.getEmployeesByDepartment(2)).thenReturn(Arrays.asList(
                new EmployeeByDepartment("Ivan", "Ivanov", 50000, 1),
                new EmployeeByDepartment("Artur", "Smirnov", 60000, null),
                new EmployeeByDepartment("Kiril", "Kirillov", 45000, 1),
                new EmployeeByDepartment("Michail", "Michailov", 55000, 1)
        ));

        Map<Integer, List<EmployeeByDepartment>> employeesByDepartment = departmentServiceImplUnderTest.getEmployeesGroupedByDepartment();

        assertNotNull(employeesByDepartment);
        assertEquals(3, employeesByDepartment.size());
        assertEquals(2, employeesByDepartment.get(1).size());
        assertEquals(1, employeesByDepartment.get(2).size());
        assertTrue(employeesByDepartment.get(null).isEmpty());
    }
    @Test
    public void testGetEmployeesByNonExistentDepartmentId() {
        List<EmployeeByDepartment> employees = new ArrayList<>();
        when(departmentService.getEmployeesByDepartment(1)).thenReturn(employees);

        List<EmployeeByDepartment> result = departmentServiceImplUnderTest.getEmployeesByDepartment(6);

        assertTrue(result.isEmpty());
    }

}