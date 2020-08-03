package com.employee.management.service;

import javax.print.SimpleDoc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.employee.management.error.DepartmentErrorCodes;
import com.employee.management.exception.DepartmentException;
import com.employee.management.helperclasses.DocPrintJobMock;
import com.employee.management.helperclasses.PrintServiceMock;
import com.employee.management.helperclasses.TestingConstants;
import com.employee.management.repository.DepartmentRepository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(PowerMockRunner.class)
// Added DepartmentServiceImpl to mock its private method
@PrepareForTest(DepartmentServiceImpl.class)
public class DepartmentServicePowerMockitoTest {

    @Mock
    private DepartmentRepository departmentRepository;

    private DepartmentServiceImpl departmentService;

    @Before
    public void setUp() {
        // created spy from powerMockito to mock private methods as Mockito spy can't do that
        departmentService = PowerMockito.spy(new DepartmentServiceImpl(departmentRepository));
    }

    @Test
    public void testPrintDetails() {
        try {
            PrintServiceMock printServiceMock = mock(PrintServiceMock.class);
            // mocking private method using PowerMockito
            PowerMockito.doReturn(printServiceMock).when(departmentService, "findPrintService");
            DocPrintJobMock docPrintJobMock = mock(DocPrintJobMock.class);
            doReturn(docPrintJobMock).when(printServiceMock).createPrintJob();
            departmentService.printDetails("Print This");
            // verifying private method should be called
            PowerMockito.verifyPrivate(departmentService).invoke("findPrintService");
            verify(printServiceMock, only()).createPrintJob();
            verify(docPrintJobMock, only()).print(Mockito.<SimpleDoc>any(), Mockito.eq(null));
        } catch (Exception e) {
            fail(TestingConstants.TEST_CASE_FAILED_MESSAGE + e.getMessage());
        }
    }

    @Test
    public void testPrintDetailsException() {
        PrintServiceMock printServiceMock = mock(PrintServiceMock.class);
        // just created spy of this, to verify print and other methods were called, although no mocking is required
        DocPrintJobMock docPrintJobMock = spy(new DocPrintJobMock());
        try {
            // mocking private method using PowerMockito
            PowerMockito.doReturn(printServiceMock).when(departmentService, "findPrintService");
            doReturn(docPrintJobMock).when(printServiceMock).createPrintJob();
            // setting throwException variable as true, so that mock print method throws PrintException
            docPrintJobMock.setThrowException(true);
            departmentService.printDetails("Print This");
            // As this is test case written for exception case, so if in case no exception comes, it should fail as
            // well.
            fail(TestingConstants.TEST_CASE_FAILED_MESSAGE_SHOULD_NOT_REACH + DepartmentException.class.getName());
        } catch (DepartmentException departmentException) {
            assertEquals(DepartmentErrorCodes.DEPARTMENT_PRINTING_EXCEPTION, departmentException.getErrorCodes());
        } catch (Exception e) {
            // If any other exception comes than expected, then fail as well
            fail(e.getClass().getName() + "exception was thrown but expected " + DepartmentException.class.getName());
        }
        try {
            // verifying private method should be called
            PowerMockito.verifyPrivate(departmentService).invoke("findPrintService");
            verify(printServiceMock, only()).createPrintJob();
            verify(docPrintJobMock).setThrowException(true);
            verify(docPrintJobMock).isThrowException();
            verify(docPrintJobMock).print(Mockito.<SimpleDoc>any(), Mockito.eq(null));
            verifyNoMoreInteractions(docPrintJobMock);
        } catch (Exception e) {
            fail(TestingConstants.TEST_CASE_FAILED_MESSAGE + e.getMessage());
        }
    }
}
