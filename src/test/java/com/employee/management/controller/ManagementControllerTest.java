package com.employee.management.controller;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.employee.management.service.ManagementService;
import com.employee.management.utility.JSONUtility;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
// Added classes for which Class's byteCode needs to be manipulated in this case.
@PrepareForTest({ManagementService.class, JSONUtility.class})
// This annotation tells PowerMock to defer the loading of classes or packages provided
@PowerMockIgnore({"javax.management.*", "javax.script.*"})
public class ManagementControllerTest {

    @Mock
    private ManagementService managementService;

    @InjectMocks
    private ManagementController managementController;

    /*
     * This is the sample test case to demonstrate mocking of final method using powerMockito
     */
    @Test
    public void testFinalMethod() {
        // mocking final method just like a normal method using Mockito only, we just need to add the class with final
        // method in @PrepareForTest annotation
        when(managementService.getRandomListOfDepartmentsOrEmployees()).thenReturn(new ArrayList<>());
        assertEquals("[]", managementController.getRandomListOfDepartmentsOrEmployees());
        verify(managementService, only()).getRandomListOfDepartmentsOrEmployees();
    }

    /*
     * This is the sample test case to demonstrate mocking of static method using powerMockito
     */
    @Test
    public void testSt() {
        ArrayList<Object> list = new ArrayList<>();
        when(managementService.getRandomListOfDepartmentsOrEmployees()).thenReturn(list);
        // First we specify the class whose static method we will be mocking, the mocking the behavior of static method
        // using PowerMockito in next line
        PowerMockito.mockStatic(JSONUtility.class);
        PowerMockito.when(JSONUtility.stringify(list)).thenReturn("Converted String");

        assertEquals("Converted String", managementController.getRandomListOfDepartmentsOrEmployees());
        verify(managementService, only()).getRandomListOfDepartmentsOrEmployees();

        // Verification of static method is also done in 2 lines, i.e. first we specify class having static method and
        // then in second line we write the method call how it should have called in the test case.
        PowerMockito.verifyStatic(JSONUtility.class, only());
        JSONUtility.stringify(list);
    }
}
