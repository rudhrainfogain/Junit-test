package com.employee.management.helperclasses;


import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.ServiceUIFactory;
import javax.print.attribute.Attribute;
import javax.print.attribute.AttributeSet;
import javax.print.attribute.PrintServiceAttribute;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.event.PrintServiceAttributeListener;

import sun.print.SunPrinterJobService; // NOSONAR

/**
 * Copyright (c) 2019 FedEx. All Rights Reserved.<br>
 * <br>
 * Theme - Core Retail Peripheral Services<br>
 * Feature - Peripheral Services - Implement application performance monitoring.<br>
 * Description - This class is used for mocking methods of {@link PrintService} class
 * 
 * @author Abhishek Singhal [3692173]
 * @version 1.0.0
 * @since Jul 30, 2019
 */
@SuppressWarnings("restriction")
public class PrintServiceMock implements PrintService, SunPrinterJobService {

    String printServiceMockName = "PrintServiceMockName";

    @Override
    public String getName() {
        return printServiceMockName;
    }

    @Override
    public DocPrintJob createPrintJob() {
        return new DocPrintJobMock();
    }

    @Override
    public void addPrintServiceAttributeListener(PrintServiceAttributeListener listener) {
        // No implementation of this method needed
    }

    @Override
    public void removePrintServiceAttributeListener(PrintServiceAttributeListener listener) {
        // No implementation of this method needed
    }

    @Override
    public PrintServiceAttributeSet getAttributes() {

        return null;
    }

    @Override
    public <T extends PrintServiceAttribute> T getAttribute(Class<T> category) {

        return null;
    }

    @Override
    public DocFlavor[] getSupportedDocFlavors() {

        return new DocFlavor[] {};
    }

    @Override
    public boolean isDocFlavorSupported(DocFlavor flavor) {

        return true;
    }

    @Override
    public Class<?>[] getSupportedAttributeCategories() {

        return new Class<?>[] {};
    }

    @Override
    public boolean isAttributeCategorySupported(Class<? extends Attribute> category) {

        return false;
    }

    @Override
    public Object getDefaultAttributeValue(Class<? extends Attribute> category) {

        return null;
    }

    @Override
    public Object getSupportedAttributeValues(Class<? extends Attribute> category, DocFlavor flavor,
                    AttributeSet attributes) {

        return null;
    }

    @Override
    public boolean isAttributeValueSupported(Attribute attrval, DocFlavor flavor, AttributeSet attributes) {

        return false;
    }

    @Override
    public AttributeSet getUnsupportedAttributes(DocFlavor flavor, AttributeSet attributes) {

        return null;
    }

    @Override
    public ServiceUIFactory getServiceUIFactory() {

        return null;
    }

    @Override
    public boolean usesClass(Class arg0) {

        return true;
    }

}
