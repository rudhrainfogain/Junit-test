package com.employee.management.repository;

import org.springframework.context.annotation.Profile;
import org.springframework.data.repository.CrudRepository;
import com.employee.management.model.Department;


public interface DepartmentRepository extends CrudRepository<Department, Integer>{

}
