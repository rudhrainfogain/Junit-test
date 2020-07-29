package com.employee.management.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.employee.management.error.DepartmentErrorCodes;
import com.employee.management.exception.DepartmentException;
import com.employee.management.model.Department;
import com.employee.management.service.DepartmentServiceImpl;

/* To Enable Mockito Annotations :
 Need to annotate the JUnit test with a runner– MockitoJUnitRunner
Example:

@RunWith(MockitoJUnitRunner.class)
public class MockitoAnnotationTest {
    ...
}

Alternatively we can enable these annotations programmatically as well, by invoking MockitoAnnotations.initMocks()
Example:
@Before
public void init() {
    MockitoAnnotations.initMocks(this);
}
*/
public class DepartmentControllerTest {
	/*
	 * @Mock Annotation is used to create and inject mocked instances without having
	 * to call Mockito.mockmanually.
	 * 
	 */

	@Mock
	DepartmentServiceImpl departmentService;

	/*
	 * @InjectMocksannotation – to inject mock fields into the tested object
	 * automatically.
	 */
	@InjectMocks
	DepartmentController DepartmentController;

	static Department departmentOne;

	static Department departmentTwo;
	
	Department departmentThree;
	static Optional<Department> deparmentOptional;

	static List<Department> list;

	// Sample test case for the before class annotation

	@BeforeClass
	public static void beforeClassTestCase() {

		/**
		 * This method will be called only once per test class. It will be called before
		 * executing test.
		 */
		list = new ArrayList<Department>();
		departmentOne = new Department(7, "XYZ Department", "XYZ");
		departmentTwo = new Department(4, "ABC Department", "ABC");
		list.add(departmentOne);
		list.add(departmentTwo);
		deparmentOptional = Optional.of(departmentOne);
		System.out.println("Using @BeforeClass ,executed after all test cases");
	}

	/**
	 * This method will be called be before every test cases execution.
	 * 
	 */
	@Before
	public void setUp() {
		/*
		 * initMocks Initializes objects annotated with Mockito annotations for given
		 * testClass: @Mock, @Spy, @InjectMocks
		 */
		MockitoAnnotations.initMocks(this);
		System.out.println("Executed before every Test cases");
	}
	/*
	 * Sample example that call the real method of the mocked class here after
	 * mocking the Department service class we can call the actual method by calling
	 * the doCallRealMethod Method
	 */

	@Test
	public void testGetHarcodedEmployeeList() {
		Mockito.doCallRealMethod().when(departmentService).getHarcodedEmployeeList();
		List<Department> dptlist = DepartmentController.getHarcodedDepartment();
		Assert.assertNotNull(dptlist);
		verify(departmentService).getHarcodedEmployeeList();
	}

	@Test
	public void testGetAllEmployees() {
		when(departmentService.getAllDepartments()).thenReturn(list);
		List<Department> departmentList = DepartmentController.getAllDepartment();
		Assert.assertEquals(list.size(), departmentList.size());
		Assert.assertEquals(list, departmentList);
		verify(departmentService).getAllDepartments();
		System.out.println("testGetAllEmployees");
	}

	@Test
	public void testGetEmployee() {
		when(departmentService.getDepartment(Mockito.anyInt())).thenReturn(departmentOne);
		Department department = DepartmentController.getDepartment(7);
		Assert.assertEquals(departmentOne.getDepartment_Name(), department.getDepartment_Name());
		Assert.assertEquals(departmentOne.getShort_Name(), department.getShort_Name());
		Assert.assertEquals(departmentOne.getDepartment_ID(), department.getDepartment_ID());
		verify(departmentService).getDepartment(Mockito.anyInt());
		System.out.println("testGetEmployee");
	}

	/*
	 * This annotation can be used if you want to handle some exception during test
	 * execution. For, e.g., if you want to check whether a particular method is
	 * throwing specified exception or not.
	 * 
	 */

	@Test(expected = DepartmentException.class)
	public void testGetEmployeeDepaertmentException() {
		when(departmentService.getDepartment(Mockito.anyInt())).thenReturn(departmentOne);
		DepartmentController.getDepartment(-3);
	}

	@Test
	public void testAddEmployee() {
		Mockito.doNothing().when(departmentService).addDepartment(Mockito.any(Department.class));
		DepartmentController.addDepartment(departmentOne);
		verify(departmentService).addDepartment(Mockito.any(Department.class));
		System.out.println("testAddEmployee");
	}

	@Test
	public void testUpdateEmployee() {
		Mockito.doNothing().when(departmentService).updateDepartment(Mockito.any(Department.class), Mockito.anyInt());
		DepartmentController.updateDepartment(departmentOne, 9);
		verify(departmentService).updateDepartment(Mockito.any(Department.class), Mockito.anyInt());
		System.out.println("testUpdateEmployee");
	}

	@Test
	public void testDeleteAllEmployees() {
		Mockito.doNothing().when(departmentService).deleteAllDepartment();
		DepartmentController.deleteAllDepartments();
		verify(departmentService).deleteAllDepartment();
		System.out.println("testDeleteAllEmployees");
	}

	@Test
	public void testDeleteEmployeeById() {
		Mockito.doNothing().when(departmentService).deleteDepartmentByID(Mockito.anyInt());
		DepartmentController.deleteDepartmentByID(departmentOne, 7);
		verify(departmentService).deleteDepartmentByID(Mockito.anyInt());
		System.out.println("testDeleteEmployeeById");
	}

	/*
	 * Uncomment the Ignore Annotation to show how the test case get Ignore while
	 * executing.
	 */
	// @Ignore
	@Test
	public void testPatchEmployeeById() {
		Mockito.doNothing().when(departmentService).patchDepartment(Mockito.any(Department.class), Mockito.anyInt());
		DepartmentController.patchDepartmentByID(departmentOne, 7);
		verify(departmentService).patchDepartment(Mockito.any(Department.class), Mockito.anyInt());
		System.out.println("testPatchEmployeeById");
	}

	/*
	 * This is the sample test case to test the exception scenario using try catch
	 * in the test method
	 * 
	 */
	@Test
	public void addDepartmentNegativeDepartmentIdException() {
		/* Sample example verify for the zero interaction */
		DepartmentErrorCodes expectedErrorCode = DepartmentErrorCodes.DEPARTMENT_NAME_EXCEPTION;
		try {
			departmentThree = new Department(-4, "ABC Department", "ABC");
			DepartmentController.addDepartment(departmentThree);
		} catch (DepartmentException exception) {
			assertEquals(expectedErrorCode, exception.getErrorCodes());
			verifyZeroInteractions(departmentService);
		}
	}

	// Sample test case for the after class annotation

	@AfterClass
	public static void aftreClassTestCase() {
		/**
		 * This method will be called only once per test class. It will be called after
		 * executing test.
		 */
		list = null;
		System.out.println("Using @AfterClass ,executed after all test cases");
	}

}
