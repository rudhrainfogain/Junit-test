package com.employee.management.service;

import static org.mockito.Mockito.verify;

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
import com.employee.management.repository.EmployeeRepository;

public class EmployeeServiceTest {

	@Mock
	EmployeeRepository employeeRepository;

	@InjectMocks
	EmployeeService employeeService;

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
		Mockito.when(employeeRepository.findAll()).thenReturn(list);
		List<Employee> emplist = employeeService.getAllEmployees();
		Assert.assertEquals(list.size(), emplist.size());
		Assert.assertEquals(list, emplist);
		verify(employeeRepository).findAll();
	}

	@Test
	public void testGetEployeesById() {
		Mockito.when(employeeRepository.findById(Mockito.anyInt())).thenReturn(employeeOptional);
		Employee emp = employeeService.getEmployee(9);
		Assert.assertEquals(employeeOne, emp);
		Assert.assertEquals(employeeOne.getFirstName(), emp.getFirstName());
		Assert.assertEquals(employeeOne.getEmployeeID(), emp.getEmployeeID());
		verify(employeeRepository).findById(Mockito.anyInt());

	}

	@Test
	public void testSaveUser() {
		Mockito.when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employeeTwo);
		employeeService.addEmployee(employeeTwo);
		verify(employeeRepository).save(Mockito.any(Employee.class));
	}

	@Test
	public void testdeleteAllEmployees() {
		Mockito.doNothing().when(employeeRepository).deleteAll();
		employeeService.deleteAllEmployees();
		verify(employeeRepository).deleteAll();
	}

	@Test
	public void testdeleteEmployeeById() {
		Mockito.doNothing().when(employeeRepository).deleteById(Mockito.anyInt());
		employeeService.deleteEmployeeByID(9);
		verify(employeeRepository).deleteById(Mockito.anyInt());
	}

	@Test
	public void testUpdateEmployee() {
		employeeThree = new Employee("Tylor", "John", department);
		employeeThree.setEmployeeID(8);
		Mockito.when(employeeRepository.save(Mockito.any(Employee.class))).thenReturn(employeeTwo);
		employeeService.updateEmployee(employeeThree, 8);
		verify(employeeRepository).save(Mockito.any(Employee.class));
	}

}
