package com.employee.management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.employee.management.model.Department;
import com.employee.management.repository.DepartmentRepository;

@Service
@Profile("!mock")
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	private DepartmentRepository departmentRepository;
	

	// fetching all department
	@Override
	public List<Department> getAllDepartments(){
		List<Department> depts = (List<Department>)departmentRepository.findAll(); 
		return depts;
	}
	
	// fetching department by id
	@Override
	public Department getDepartment(int id){
		return departmentRepository.findById(id).get();
	}
	
	// inserting department
	@Override
	public void addDepartment(Department d) {
		departmentRepository.save(d);
	}
	
	// updating department by id
	@Override
	public void updateDepartment(Department d, int id){
		if(id == d.getDepartment_ID()) {
			departmentRepository.save(d);
		}
	}
	
	// deleting all departments
	@Override
	public void deleteAllDepartment(){
		departmentRepository.deleteAll();
	}
	
	// deleting department by id
	@Override
	public void deleteDepartmentByID(int id){
		departmentRepository.deleteById(id);
	}
	
	//patching/updating department by id
	@Override
	public void patchDepartment(Department d, int id) {
		if(id == d.getDepartment_ID()) {
			departmentRepository.save(d);
		}
	}
	@Override
	public List<Department> getHarcodedEmployeeList() {
		List<Department> list = new ArrayList<>();
		list.add(new Department(10,"XYZ Departemnt","XYZ"));
		return list;
		
	}
}
