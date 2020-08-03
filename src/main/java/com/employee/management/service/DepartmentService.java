package com.employee.management.service;

import java.util.List;

import com.employee.management.model.Department;

public interface DepartmentService {

	// fetching all department
	public List<Department> getAllDepartments();
	
	// fetching department by id
	public Department getDepartment(int id);
	// inserting department
	public void addDepartment(Department d) ;
	
	// updating department by id
	public void updateDepartment(Department d, int id);
	
	// deleting all departments
	public void deleteAllDepartment();
	
	// deleting department by id
	public void deleteDepartmentByID(int id);
	
	//patching/updating department by id
	public void patchDepartment(Department d, int id);
	
	public List<Department> getHarcodedEmployeeList();
	
	public void printDetails(String dataToPrint);
}
