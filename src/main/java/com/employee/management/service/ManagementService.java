package com.employee.management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.management.constants.ManagementReponseType;

@Service

public class ManagementService {
    static final Logger logger = LogManager.getLogger(ManagementService.class.getName());

    private static Random rd;
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    @Autowired
    public ManagementService(final DepartmentService departmentService, final EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    static {
        rd = new Random();
    }

    public List<Object> getListOfDepartmentsOrEmployees(ManagementReponseType managementReponseType) {
        List<Object> returnList = new ArrayList<>();
        switch (managementReponseType) {
            case DEPARTMENT:
                returnList.addAll(departmentService.getAllDepartments());
                break;
            case EMPLOYEE:
                returnList.addAll(employeeService.getAllEmployees());
                break;
            default:
                logger.warn("Invalid request");
        }
        return returnList;
    }

    public final List<Object> getRandomListOfDepartmentsOrEmployees() {
        List<Object> returnList = new ArrayList<>();
        if (rd.nextInt(5) >= 3) {
            returnList.addAll(departmentService.getAllDepartments());
        } else {
            returnList.addAll(employeeService.getAllEmployees());
        }
        return returnList;
    }
}
