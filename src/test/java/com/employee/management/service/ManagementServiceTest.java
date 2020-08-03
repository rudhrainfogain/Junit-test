package com.employee.management.service;

import java.util.ArrayList;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.employee.management.constants.ManagementReponseType;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
//This annotation tells PowerMock to defer the loading of classes or packages provided
@PowerMockIgnore({"javax.management.*", "javax.script.*"})
public class ManagementServiceTest {

    @Mock
    private DepartmentService departmentService;
    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private ManagementService managementService;

    @Test
    public void testGetListOfDepartmentsOrEmployees() {
        // asserting that empty list is fetched when department service's methods are not mocked as they will return
        // default values. When return type is List, Mockito returns empty list by default. So commenting next line will
        // also gives same result.
        when(departmentService.getAllDepartments()).thenReturn(new ArrayList<>());
        assertThat(managementService.getListOfDepartmentsOrEmployees(ManagementReponseType.DEPARTMENT), is(empty()));
        verify(departmentService, only()).getAllDepartments();
        verifyZeroInteractions(employeeService);
    }

    /**
     * This method shows how to mock ENUM As we need to mock ENUM, and for that static method values() needs to be
     * mocked, so we need to prepare ENUM by providing {@link ManagementReponseType} to {@link PrepareForTest} annotation so that
     * its byteCode can be manipulated.
     */
    @Test
    @PrepareForTest(ManagementReponseType.class)
    public void testGetListOfDepartmentsOrEmployeesInvalidEnum() {
        ManagementReponseType invalidReponseType = mock(ManagementReponseType.class);
        // As this ENUM has two values already, so invalid ENUM value should contain next value of it. Thats why, we set
        // value of ordinal to 2 as 0 and 1 are already taken.
        Whitebox.setInternalState(invalidReponseType, "ordinal", 2);
        // Next we need to mock behavior of static method values() which is also internally used in switch case for
        // ENUM, so we also mock this static method using PowerMockito.
        PowerMockito.mockStatic(ManagementReponseType.class);
        PowerMockito.when(ManagementReponseType.values()).thenReturn(new ManagementReponseType[] {
                        ManagementReponseType.DEPARTMENT, ManagementReponseType.EMPLOYEE, invalidReponseType});
        assertThat(managementService.getListOfDepartmentsOrEmployees(invalidReponseType), is(empty()));
        verifyZeroInteractions(departmentService);
        verifyZeroInteractions(employeeService);
        // verification of static method
        PowerMockito.verifyStatic(ManagementReponseType.class, only());
        ManagementReponseType.values();
    }

    /**
     * This method shows how to suppress static initializers of a class. And also how to change value of a field in a
     * class
     * 
     * This annotation helps to suppress a static block of one or more classes to execute. As in this case we initialise
     * {@link ManagementService#rd} variable in static block of that class. But we can stop that from executing using
     * {@link SuppressStaticInitializationFor}.
     */
    @Test
    @SuppressStaticInitializationFor("com.employee.management.service.ManagementService")
    public void testGetRandomListOfDepartmentsOrEmployees() {
        Random mockRandom = mock(Random.class);
        // To set value for static fields, first parameter should be class not object of class.
        // As we need to provide mock Random class to rd field as we have suppressed static block
        // You can verify that static block is not executed as it will throw null pointer if you comment below line
        Whitebox.setInternalState(ManagementService.class, "rd", mockRandom);
        assertThat(managementService.getRandomListOfDepartmentsOrEmployees(), is(empty()));
        // no need to mock nexTint method as it will return int's default value 0 always. Just verifying it was called.
        verify(mockRandom, only()).nextInt(5);
        verifyZeroInteractions(departmentService);
        verify(employeeService, only()).getAllEmployees();
    }

}
