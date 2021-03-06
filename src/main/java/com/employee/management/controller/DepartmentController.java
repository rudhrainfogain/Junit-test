package com.employee.management.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.employee.management.error.DepartmentErrorCodes;
import com.employee.management.exception.DepartmentException;
import com.employee.management.model.Department;
import com.employee.management.service.DepartmentService;
import com.employee.management.utility.JSONUtility;

@RestController
public class DepartmentController {

	static final Logger logger = LogManager.getLogger(DepartmentController.class.getName());

	@Autowired
	private DepartmentService departmentService;

	// displaying list of all department
	@GetMapping("/departments")
	public List<Department> getAllDepartment() {
		return departmentService.getAllDepartments();
	}

	// display HardcodedData
	@GetMapping("/hardcodeddepartments")
	public List<Department> getHarcodedDepartment() {
		if (departmentService.getHarcodedEmployeeList() != null) {
			return departmentService.getHarcodedEmployeeList();
		}
		return null;
	}

	// displaying department by id
	@GetMapping("/departments/{id}")
	public Department getDepartment(@PathVariable int id) {
		if (id <= -1) {
			throw new DepartmentException(DepartmentErrorCodes.DEPARTMENT_ID_EXCEPTION);
		} else {
			return departmentService.getDepartment(id);
		}
	}

	// inserting department
	@PostMapping("/adddepartments")
	public void addDepartment(@RequestBody Department department) {
		if (department.getDepartment_Name().isEmpty()) {
			throw new DepartmentException(DepartmentErrorCodes.DEPARTMENT_NAME_EXCEPTION);
		} else {
			departmentService.addDepartment(department);
		}
	}

	// updating department by id
	@PutMapping("/departments/{id}")
	public void updateDepartment(@RequestBody Department d, @PathVariable int id) {
		if (departmentService.getDepartment(id) != null) {
			departmentService.updateDepartment(d, id);
		}
	}

	// deleting all department
	@DeleteMapping("/departments")
	public void deleteAllDepartments() {
		departmentService.deleteAllDepartment();
	}

	// deleting department by id
	@DeleteMapping("departments/{id}")
	public void deleteDepartmentByID(@RequestBody Department d, @PathVariable int id) {
		departmentService.deleteDepartmentByID(id);
	}

	// updating/ patching department by id
	@PatchMapping("departments/{id}")
	public void patchDepartmentByID(@RequestBody Department d, @PathVariable int id) {
		departmentService.patchDepartment(d, id);
	}

	// printng department details
    @GetMapping("/print")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void printDepartmentDetails() {
        List<Department> allDepartments = departmentService.getAllDepartments();
        String stringify = JSONUtility.stringify(allDepartments);
        departmentService.printDetails(stringify);
    }
}
