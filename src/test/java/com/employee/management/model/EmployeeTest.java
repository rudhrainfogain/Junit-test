package com.employee.management.model;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class EmployeeTest {

	int employeeId;
	String firstName;
	String lastName;
	int departmentId;
	String departmentName;
	String shortName;
	Department department;
	Employee employee;

	@Before
	public void setUp() {

		departmentId = 1;
		departmentName = "IT Department";
		shortName = "IT";
		department = new Department(departmentId, departmentName, shortName);
		
		employeeId = 0;
		firstName = "John";
		lastName = "Smith";
		employee = new Employee(firstName, lastName,department);
		System.out.println("executed before every test cases");
	}

	@Test
	public void testGetEmployeetId() {
		assertNotNull(employee.getEmployeeID());
		assertEquals(employee.getEmployeeID(), employeeId);
		System.out.println("testGetEmployeetId");
	}

	@Test
	public void testSetEmployeeId() {
		employee.setEmployeeID(4);
		assertEquals(employee.getEmployeeID(), 4);
		System.out.println("testSetEmployeeId");
	} 
	

	@Ignore
	public void testGetFirstName() {
		assertNotNull(employee.getFirstName());
		assertEquals(employee.getFirstName(), firstName);
		System.out.println("testGetFirstName");
	}

	@Ignore
	public void testSetFirstName() {
		employee.setFirstName("Tylor");
		assertNotNull(employee.getFirstName());
		assertEquals(employee.getFirstName(), "Tylor");
		System.out.println("testSetFirstName");
	}

	@Ignore
	public void testGetLastName() {
		assertNotNull(employee.getLastName());
		assertEquals(employee.getLastName(), lastName);
		System.out.println("testGetLastName");
	}

	@Test
	public void testSetLastName() {
		employee.setLastName("John");
		assertNotNull(employee.getLastName());
		assertEquals(employee.getLastName(), "John");
		System.out.println("testSetLastName");
	}

	@Test
	public void testGetDepartment() {
		assertNotNull(employee.getDepartment());
		assertEquals(employee.getDepartment().getDepartment_ID(), department.getDepartment_ID());
		assertEquals(employee.getDepartment().getDepartment_Name(), department.getDepartment_Name());
		assertEquals(employee.getDepartment().getShort_Name(), department.getShort_Name());
		System.out.println("testGetDepartment");
	}
	
	@Test
	public void testSetDepartment() {
		Department department = new Department(7, "XYZ Department", "XYZ");
		employee.setDepartment(department);	
		assertNotNull(employee.getDepartment());
		assertEquals(employee.getDepartment().getDepartment_ID(), department.getDepartment_ID());
		assertEquals(employee.getDepartment().getDepartment_Name(), department.getDepartment_Name());
		assertEquals(employee.getDepartment().getShort_Name(), department.getShort_Name());
		System.out.println("testSetDepartment");
	}
	

	// Sample test case for the after class annotation

	@AfterClass
	public static void aftreClassTestCase() {
		System.out.println("Using @AfterClass ,executed after all test cases");
	}

	// Sample test case for the before class annotation

	@BeforeClass
	public static void beforeClassTestCase() {
		System.out.println("Using @BeforeClass ,executed after all test cases");
	}

}
