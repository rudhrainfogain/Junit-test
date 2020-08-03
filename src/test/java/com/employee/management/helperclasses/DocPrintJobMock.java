package com.employee.management.helperclasses;


import javax.print.Doc;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.attribute.PrintJobAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.event.PrintJobAttributeListener;
import javax.print.event.PrintJobListener;

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - Peripheral Services - Design and Architecture<br>
 * Description - This class is used for mocking methods of {@link DocPrintJob} class
 * 
 * @author Divyanshu Varshney [5209377]
 * @version 1.0.0
 * @since 19-Sep-2019
 */
public class DocPrintJobMock implements DocPrintJob {

    // for tracking whether exception is thrown
    boolean throwException = false;

    public boolean isThrowException() {
        return throwException;
    }

    public void setThrowException(boolean throwException) {
        this.throwException = throwException;
    }

    @Override
    public PrintService getPrintService() {

        return null;
    }

    @Override
    public PrintJobAttributeSet getAttributes() {
        return null;
    }

    @Override
    public void addPrintJobListener(PrintJobListener listener) {
        // Since this is mocked method, so implementation is not required
    }

    @Override
    public void removePrintJobListener(PrintJobListener listener) {
        // Since this is mocked method, so implementation is not required
    }

    @Override
    public void addPrintJobAttributeListener(PrintJobAttributeListener listener, PrintJobAttributeSet attributes) {
        // Since this is mocked method, so implementation is not required
    }

    @Override
    public void removePrintJobAttributeListener(PrintJobAttributeListener listener) {
        // Since this is mocked method, so implementation is not required
    }

    @Override
    public void print(Doc doc, PrintRequestAttributeSet attributes) throws PrintException {
        // throwing print exception for mocking catch functionality
        if (isThrowException()) {
            throw new PrintException();
        }
    }

}
