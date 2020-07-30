package com.employee.management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.employee.management.model.Department;


@Service
@Profile("mock")
public class DepartmentServiceMock implements DepartmentService {

	// fetching all department
	@Override
	public List<Department> getAllDepartments() {
		List<Department> list = new ArrayList<>();
		list.add(new Department(10,"XYZ Departemnt","XYZ"));
		list.add(new Department(12,"ABC Departemnt","ABC"));
		return list;
	}

	// fetching department by id
	@Override
	public Department getDepartment(int id) {
		return new Department(10,"XYZ Departemnt","XYZ");
	}

	// inserting department
	@Override
	public void addDepartment(Department d) {

	}

	// updating department by id
	@Override
	public void updateDepartment(Department d, int id) {

	}

	// deleting all departments
	@Override
	public void deleteAllDepartment() {

	}

	// deleting department by id
	@Override
	public void deleteDepartmentByID(int id) {

	}

	// patching/updating department by id
	@Override
	public void patchDepartment(Department d, int id) {

	}

	@Override
	public List<Department> getHarcodedEmployeeList() {
		// TODO Auto-generated method stub
		return null;
	}

}
