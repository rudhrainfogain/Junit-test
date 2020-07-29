package com.employee.management.model;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class DepartmentTest {

	int departmentId;
	String departmentName;
	String shortName;
	Department department;

	@Before
	public void setUp() {

		departmentId = 1;
		departmentName = "IT Department";
		shortName = "IT";
		department = new Department(departmentId, departmentName, shortName);
		System.out.println("executed before every test cases");
	}

	@Test
	public void testGetDepartmentId() {
		assertNotNull(department.getDepartment_ID());
		assertEquals(department.getDepartment_ID(), departmentId);
		System.out.println("testGetEmailAddress");
	}

	@Test
	public void testSetDepartMentId() {
		department.setDepartment_ID(2);
		assertEquals(department.getDepartment_ID(), 2);
		System.out.println("testGetDepartmentId");
	} 
	// Un comment to show Sample falling test case test case

	/*@Test
	public void test() {
		fail("Falling Test Cases");
	}
*/
	// Sample test case that get ignore at the time of execution

	@Ignore
	public void testIgnoreSample() {
		assertNotNull(department.getDepartment_Name());
		assertEquals(department.getDepartment_Name(), departmentName);
		System.out.println("testIgnoreSample");
	}

	@Ignore
	public void testGetDepartmentName() {
		assertNotNull(department.getDepartment_Name());
		assertEquals(department.getDepartment_Name(), departmentName);
		System.out.println("testGetDepartmentName");
	}

	@Ignore
	public void testSetDepartmentName() {
		department.setDepartment_Name("Admin Department");
		assertNotNull(department.getDepartment_Name());
		assertEquals(department.getDepartment_Name(), "Admin Department");
		System.out.println("testSetDepartmentName");
	}

	@Ignore
	public void testGetShortName() {
		assertNotNull(department.getShort_Name());
		assertEquals(department.getShort_Name(), shortName);
	}

	@Ignore
	public void testSetShortName() {
		department.setShort_Name("AD");
		assertNotNull(department.getShort_Name());
		assertEquals(department.getShort_Name(), shortName);
	}

	// Sample test case for testing the exception whether that particular metho throws exception or not

	@Test(expected=ArithmeticException.class)

	public void arithmeticTest() {
		int a = 1 / 0;
		System.out.println("Using @Test(expected) ,it will check for specified exception during its execution");

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
