package com.employee.management.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.employee.management.model.Department;
import com.employee.management.repository.DepartmentRepository;
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
@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceImplTest {
	@Mock
	DepartmentRepository departmentRepository;

	@InjectMocks
	DepartmentServiceImpl departmentService;

	Department departmentOne, departmentTwo;
	

	/*
	 * @Spy annotation is used to create a real object and spy on that real object.
	 * A spy helps to call all the normal methods of the object while still tracking
	 * every interaction
	 * 
	 * Difference Between Mock And Spy
	 * 
	 * When using @Mock, mockito creates a bare-bones shell instance of the Class,
	 * This is not a real object and does not maintain the state changes to it.
	 * 
	 * When using @Spy, mockito creates a real instance of the class and track every
	 * interactions with it. It maintains the state changes to it.
	 */
	@Spy
	List<Department> list = new ArrayList<>();

	@Before
	public void setUp() {
		departmentOne = new Department(7, "XYZ Department", "XYZ");
		departmentTwo = new Department(4, "ABC Department", "ABC");
	}

	/* Sample example to show the Usage of the SPY */
	@Test
	public void testGetAllDepartments() {
		list.add(departmentOne);
		list.add(departmentTwo);
		Mockito.when(departmentRepository.findAll()).thenReturn(list);
		List<Department> dptlist = departmentService.getAllDepartments();
		Assert.assertEquals(list.size(), dptlist.size());
		Assert.assertEquals(list, dptlist);
		Mockito.verify(list).add(departmentOne);
		Mockito.verify(list).add(departmentTwo);
		verify(departmentRepository).findAll();
		assertEquals(2, list.size());
		//Mocking the behaviour of the size method of the list
	    Mockito.doReturn(100).when(list).size();
	    assertEquals(100, list.size());
	}

	@Test
	public void testGetEployeesById() {
		Optional<Department> deparmentOptional = Optional.of(departmentOne);
		Mockito.when(departmentRepository.findById(Mockito.anyInt())).thenReturn(deparmentOptional);
		Department department = departmentService.getDepartment(7);
		Assert.assertEquals(departmentOne, department);
		Assert.assertEquals(departmentOne.getDepartment_ID(), department.getDepartment_ID());
		Assert.assertEquals(departmentOne.getDepartment_Name(), department.getDepartment_Name());
		verify(departmentRepository).findById(Mockito.anyInt());

	}

	@Test
	public void testSaveUser() {
		Mockito.when(departmentRepository.save(Mockito.any(Department.class))).thenReturn(departmentOne);
		departmentService.addDepartment(departmentOne);
		verify(departmentRepository).save(Mockito.any(Department.class));
	}

	/* Sample example to show how to mock void method */
	@Test
	public void testdeleteAllEmployees() {
		Mockito.doNothing().when(departmentRepository).deleteAll();
		departmentService.deleteAllDepartment();
		verify(departmentRepository).deleteAll();
	}

	/* Sample example to show how to mock void method */
	@Test
	public void testdeleteEmployeeById() {
		Mockito.doNothing().when(departmentRepository).deleteById(Mockito.anyInt());
		departmentService.deleteDepartmentByID(4);
		verify(departmentRepository).deleteById(Mockito.anyInt());
	}

	@Test
	public void testUpdateEmployee() {
		Department deparmentThree = new Department(7, "PQR Department", "PQR");
		Mockito.when(departmentRepository.save(Mockito.any(Department.class))).thenReturn(deparmentThree);
		departmentService.updateDepartment(departmentOne, 7);
		verify(departmentRepository).save(Mockito.any(Department.class));
	}

}
