package com.employee.management.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.constants.ManagementReponseType;
import com.employee.management.service.ManagementService;
import com.employee.management.utility.JSONUtility;

@RestController
public class ManagementController {

	static final Logger logger = LogManager.getLogger(ManagementController.class.getName());

	@Autowired
	private ManagementService managementService;

	@GetMapping(value = "/getRandomList")
	public String getRandomListOfDepartmentsOrEmployees() {
        return JSONUtility.stringify(managementService.getRandomListOfDepartmentsOrEmployees());
	}

	@GetMapping("/getList/{type}")
	public String addDepartment(@PathVariable("type") ManagementReponseType managementReponseType) {
	    return JSONUtility.stringify(managementService.getListOfDepartmentsOrEmployees(managementReponseType));
	}

}
