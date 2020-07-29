package com.employee.management.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.employee.management.model.Department;
import com.employee.management.model.Employee;
import com.employee.management.service.EmployeeService;

public class EmployeeControllerTest {

	@Mock
	EmployeeService employeeService;

	@InjectMocks
	EmployeeController employeeController;

	Employee employeeOne, employeeTwo, employeeThree;
	Department department;
	Optional<Employee> employeeOptional;

	List<Employee> list;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		department = new Department(7, "XYZ Department", "XYZ");
		employeeOne = new Employee("John", "Smith", department);
		employeeOne.setEmployeeID(9);
		employeeTwo = new Employee("Tylor", "John", department);
		list = new ArrayList<Employee>();
		list.add(employeeTwo);
		list.add(employeeOne);
		employeeOptional = Optional.of(employeeOne);
	}

	@Test
	public void testGetAllEmployees() {
		when(employeeService.getAllEmployees()).thenReturn(list);
		List<Employee> emplist = employeeController.getAllEmployee();
		Assert.assertEquals(list.size(), emplist.size());
		Assert.assertEquals(list, emplist);
		verify(employeeService).getAllEmployees();
	}

	@Test
	public void testGetEmployee() {
		when(employeeService.getEmployee(Mockito.anyInt())).thenReturn(employeeOne);
		Employee emp = employeeController.getEmployee(9);
		Assert.assertEquals(employeeOne.getFirstName(), emp.getFirstName());
		Assert.assertEquals(employeeOne.getEmployeeID(), emp.getEmployeeID());
		Assert.assertEquals(employeeOne.getLastName(), emp.getLastName());
		verify(employeeService).getEmployee(Mockito.anyInt());
	}

	@Test
	public void testAddEmployee() {
		Mockito.doNothing().when(employeeService).addEmployee(Mockito.any(Employee.class));
		employeeController.addEmployees(employeeOne);
		verify(employeeService).addEmployee(Mockito.any(Employee.class));
	}

	@Test
	public void testUpdateEmployee() {
		Mockito.doNothing().when(employeeService).updateEmployee(Mockito.any(Employee.class), Mockito.anyInt());
		employeeController.updateEmployee(employeeOne, 9);
		verify(employeeService).updateEmployee(Mockito.any(Employee.class), Mockito.anyInt());
	}

	@Test
	public void testDeleteAllEmployees() {
		Mockito.doNothing().when(employeeService).deleteAllEmployees();
		employeeController.deleteAllEmployees();
		verify(employeeService).deleteAllEmployees();
	}

	@Test
	public void testDeleteEmployeeById() {
		Mockito.doNothing().when(employeeService).deleteEmployeeByID(Mockito.anyInt());
		employeeController.deleteEmployeeByID(employeeOne, 9);
		verify(employeeService).deleteEmployeeByID(Mockito.anyInt());
	}

	@Test
	public void testPatchEmployeeById() {
		Mockito.doNothing().when(employeeService).patchEmployee(Mockito.any(Employee.class), Mockito.anyInt());
		employeeController.patchEmployeeByID(employeeOne, 9);
		verify(employeeService).patchEmployee(Mockito.any(Employee.class), Mockito.anyInt());
	}
}
